package io.file;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by kevin on 2016/9/25.
 */
public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtil.class);

    /**
     * 读取文件，转成String
     *
     * @param path
     * @return
     */
    public static String readFile(String path) {
        String fileContent = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            fileContent = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    /**
     * 写文件到指定路径
     */
    public static void writeFile(String path, String fileName, String fileContent) {
        PrintWriter writer = newWriter(path, fileName);
        writer.print(fileContent);
        writer.flush();
        // 首先关闭文件输出流
        closeWriter(writer);
    }

    /**
     * 创建一个输出流
     */
    private static PrintWriter newWriter(String path, String fileName) {
        PrintWriter logWriter = null;
        try {
            // 生成文件对象，如果文件不存在，要创建新文件。
            File file = new File(path);

            if (!file.exists()) {
                File parent = file.getParentFile();

                if (parent != null && !parent.exists()) {
                    if (!file.mkdirs()) {
                        logger.error("create dir fail " + path);
                    }
                }

                if (!file.mkdir()) {
                    logger.error("create file fail " + path + fileName);
                }
            }

            // 打开输出
            logWriter = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(path + fileName), "GBK"), true);
        } catch (IOException ioe) {
            // log
            logger.error("create file fail " + ioe.getMessage());
            logWriter = null;
        }

        return logWriter;
    }

    /**
     * 关闭流
     */
    private static void closeWriter(PrintWriter logWriter) {
        if (null != logWriter) {
            try {
                logWriter.close();
            } catch (Exception e) {
                logger.error("close fileWriter fail " + e.getMessage());
            }
        }
    }
}
