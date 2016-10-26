package dbutil.queryconfig.nq;

import dbutil.tools.ArrayUtils;
import dbutil.tools.IOUtils;
import dbutil.tools.StringUtils;
import dbutil.tools.XMLUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class NamedQueryHolder {

    private static Logger logger = Logger.getLogger(NamedQueryHolder.class);

    private boolean isDev = true;
    private String currentDbType = "oracle";

    public static final char NAMESPACE_DELIMITER = '.';

    private static final String FILE_PATH = "sqlfile/";
    private static final String SPECIFIC_FILENAME_PATTERN = "*_%s.xml";
    private static final String NAMESPACE = "base";

    private static NamedQueryHolder nqh = null;

    private static ConcurrentHashMap<String, NamedQueryConfig> namedQueries =
            new ConcurrentHashMap<String, NamedQueryConfig>();
    private Map<File, Long> loadedFiles = new HashMap<File, Long>();
    private long lastUpdate; // 记录上次更新文件的时间

    public static NamedQueryHolder getInstance() {
        if (nqh == null) {
            nqh = new NamedQueryHolder();
            nqh.initQueries();
        }
        return nqh;
    }

    public NamedQueryConfig get(String name) {
        if (isDev) { // 允许动态修改SQL查询
            synchronized (this) {
                if (System.currentTimeMillis() - lastUpdate > 10000) { // 十秒内不更新修改
                    checkUpdate(name);
                    lastUpdate = System.currentTimeMillis();
                }
            }
        }
        return namedQueries.get(name);
    }

    private void checkUpdate(String name) {
        // 先通过文件日期检查文件更新
        for (Entry<File, Long> e : loadedFiles.entrySet()) {
            File file = e.getKey();
            if (file.lastModified() > e.getValue()) { // 修改过了
                if (isDev) {
                    logger.info("refresh named queries in file <" + file.toString() + ">");
                }
                loadFile(namedQueries, file);
            }
        }
    }

    /**
     * 初始化全部查询
     */
    private synchronized void initQueries() {
        long start = System.currentTimeMillis();
        if (!namedQueries.isEmpty()) {
            return;
        }
        loadQueriesFromFiles(namedQueries);
        lastUpdate = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        logger.info("load sqlfile cost[" + (end - start) + "]ms");
    }

    private void loadQueriesFromFiles(Map<String, NamedQueryConfig> result) {
        try {
            loadQueriesFromCurrentDbFiles(result);

        } catch (IOException e) {
            logger.error(e);
        }
    }

    private void loadQueriesFromCurrentDbFiles(Map<String, NamedQueryConfig> result)
            throws IOException {
        String currentFilePattern = String.format(SPECIFIC_FILENAME_PATTERN, currentDbType);
        String currentFilePatternWithPath = FILE_PATH.concat(currentFilePattern);

        URL[] folders = ArrayUtils.toArray(
                NamedQueryHolder.class.getClassLoader().getResources(FILE_PATH),
                URL.class);
        for (URL item : folders) {
            File currentItem = IOUtils.urlToFile(item);
            if (currentItem == null) {
                continue;
            }

            loadQueriesFromCurrentDbFile(result, currentItem, currentFilePattern,
                    currentFilePatternWithPath);
        }
    }

    private void loadQueriesFromCurrentDbFile(Map<String, NamedQueryConfig> result,
                                              File currentItem, String currentFilePattern, String currentFilePatternWithPath)
            throws IOException {
        if (currentItem.isDirectory()) {
            for (File xml : IOUtils.listFilesLike(currentItem, currentFilePattern)) {
                logger.info("Loading sqlfile: " + xml.getAbsolutePath());
                loadFile(result, xml);
            }
        } else {
            logger.warn(currentItem.getAbsolutePath()
                    + " is not a directory, no named-query files will be loaded!");
        }
    }

    private synchronized void loadFile(Map<String, NamedQueryConfig> result, File file) {
        loadedFiles.put(file, file.lastModified());
        try {
            Document doc = XMLUtils.loadDocument(file);
            buildNQ(result, doc);
        } catch (SAXException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    private void buildNQ(Map<String, NamedQueryConfig> result, Document doc) {
        for (Element ele : XMLUtils.childElements(doc.getDocumentElement(), "package")) {
            String namespace = XMLUtils.attrib(ele, NAMESPACE);
            NamedQueryConfig nq;
            for (Element e : XMLUtils.toElementList(ele.getChildNodes())) {
                String name = XMLUtils.attrib(e, "name");
                String type = XMLUtils.attrib(e, "type");
                String sql = XMLUtils.nodeText(e);
                int size = StringUtils.toInt(XMLUtils.attrib(e, "fetch-size"), 0);
                String key = StringUtils.isBlank(namespace) ?
                        name : namespace + NAMESPACE_DELIMITER + name;
                nq = new NamedQueryConfig(key, sql, type, size);
                nq.setTag(XMLUtils.attrib(e, "sfName"));
                result.put(nq.getName(), nq);
            }
        }
    }

}