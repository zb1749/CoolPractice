package dbutil.tools;


import dbutil.tools.depend.XmlFixedReader;
import dbutil.tools.depend.CharsetName;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class XMLUtils {
    private static final Logger logger = Logger.getLogger(XMLUtils.class);

    private static XPathFactory xp = XPathFactory.newInstance();

    public enum NodeType {
        ELEMENT_NODE, ATTRIBUTE_NODE, TEXT_NODE, CDATA_SECTION_NODE, ENTITY_REFERENCE_NODE, ENTITY_NODE, PROCESSING_INSTRUCTION_NODE, COMMENT_NODE, DOCUMENT_NODE, DOCUMENT_TYPE_NODE, DOCUMENT_FRAGMENT_NODE, NOTATION_NODE
    }

    private static NodeType getType(Node node) {
        return NodeType.values()[node.getNodeType() - 1];
    }

    /**
     * 载入XML文档
     *
     * @param file 文件
     * @return Document
     * @throws SAXException
     * @throws IOException
     */
    public static Document loadDocument(File file) throws SAXException, IOException {
        return loadDocument(file, true);
    }

    /**
     * 载入XML文件
     *
     * @param file          文件
     * @param ignorComments 是否忽略掉XML中的注释
     * @return Document
     * @throws SAXException
     * @throws IOException
     */
    public static Document loadDocument(File file, boolean ignorComments) throws SAXException, IOException {
        InputStream in = IOUtils.getInputStream(file);
        try {
            Document document = loadDocument(in, null, true);
            return document;
        } finally {
            in.close();
        }
    }

    /**
     * 载入XML文档
     *
     * @param in           输入流
     * @param charSet      编码
     * @param ignorComment 跳过注释节点
     * @return
     * @throws SAXException
     * @throws IOException
     */
    public static Document loadDocument(InputStream in, String charSet, boolean ignorComment) throws SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        // Text节点，并将其附加到相邻（如果有）的文本节点，开启后解析更方便，但无法还原
        dbf.setIgnoringComments(ignorComment);
        dbf.setValidating(false);
        dbf.setNamespaceAware(false);
        try {
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (ParserConfigurationException e) {
            logger.warn("Your xerces implemention is too old that does not support 'load-dtd-grammar' & 'load-external-dtd' feature.");
        }
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(new EH());
            InputSource is = null;
            // 解析流来获取charset
            if (charSet == null) {// 读取头200个字节来分析编码
                byte[] buf = new byte[200];
                PushbackInputStream pin = new PushbackInputStream(in, 200);
                in = pin;
                int len = pin.read(buf);
                if (len > 0) {
                    pin.unread(buf, 0, len);
                    charSet = getCharsetInXml(buf, len);
                }
            }
            if (charSet != null) {
                is = new InputSource(new XmlFixedReader(new InputStreamReader(in, charSet)));
                is.setEncoding(charSet);
            } else { // 自动检测编码
                Reader reader = new InputStreamReader(in, "UTF-8");// 为了过滤XML当中的非法字符，所以要转换为Reader，又为了转换为Reader，所以要获得XML的编码
                is = new InputSource(new XmlFixedReader(reader));
            }
            Document doc = db.parse(is);
            doc.setXmlStandalone(true);// 设置为True保存时才不会出现讨厌的standalone="no"
            return doc;
        } catch (ParserConfigurationException x) {
            throw new Error(x);
        }
    }

    public static String getCharsetInXml(byte[] buf, int len) {
        buf = ArrayUtils.subarray(buf, 0, len);
        String s = new String(buf).toLowerCase();
        int n = s.indexOf("encoding=");
        if (n > -1) {
            s = s.substring(n + 9);
            if (s.charAt(0) == '\"' || s.charAt(0) == '\'') {
                s = s.substring(1);
            }
            n = StringUtils.indexOfAny(s, "\"' ><");
            if (n > -1) {
                s = s.substring(0, n);
            }
            if (StringUtils.isEmpty(s)) {
                return null;
            }
            s = CharsetName.getStdName(s);
            return s;
        } else {
            return null;
        }
    }

    /**
     * 得到节点下，具有指定标签的Element。(只搜索一层)
     *
     * @param node
     * @param tagName
     *            ,标签，如果为null表示返回全部Element
     * @return
     */
    public static List<Element> childElements(Node node, String... tagName) {
        if (node == null)
            throw new NullPointerException("the input node can not be null!");
        List<Element> list = new ArrayList<Element>();
        NodeList nds = node.getChildNodes();
        if (tagName.length == 0 || tagName[0] == null) {// 预处理，兼容旧API
            tagName = null;
        }
        for (int i = 0; i < nds.getLength(); i++) {
            Node child = nds.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) child;
                if (tagName == null || ArrayUtils.contains(tagName, e.getNodeName())) {
                    list.add(e);
                }
            } else if (child.getNodeType() == Node.CDATA_SECTION_NODE) {
            } else if (child.getNodeType() == Node.COMMENT_NODE) {
            } else if (child.getNodeType() == Node.DOCUMENT_FRAGMENT_NODE) {

            } else if (child.getNodeType() == Node.DOCUMENT_NODE) {

            } else if (child.getNodeType() == Node.DOCUMENT_TYPE_NODE) {
            } else if (child.getNodeType() == Node.ATTRIBUTE_NODE) {
            } else if (child.getNodeType() == Node.TEXT_NODE) {
            }
        }
        return list;
    }

    /**
     * 获得属性值
     *
     * @param e
     * @param attributeName
     * @return
     */
    public static String attrib(Element e, String attributeName) {
        if (!e.hasAttribute(attributeName))
            return null;
        String text = e.getAttribute(attributeName);
        return (text == null) ? null : StringEscapeUtils.unescapeHtml(text.trim());
    }

    /**
     * 将Nodelist转换为Element List
     *
     * @param nds
     * @return
     */
    public static List<Element> toElementList(NodeList nds) {
        List<Element> list = new ArrayList<Element>();
        for (int i = 0; i < nds.getLength(); i++) {
            Node child = nds.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                list.add((Element) child);
            }
        }
        return list;
    }

    /**
     * 获取指定元素的文本(Trimed)
     *
     * @param element
     * @return
     */
    public static String nodeText(Node element) {
        Node first = first(element, Node.TEXT_NODE, Node.CDATA_SECTION_NODE);
        if (first != null && first.getNodeType() == Node.CDATA_SECTION_NODE) {
            return ((CDATASection) first).getTextContent();
        }
        StringBuilder sb = new StringBuilder();
        if (first == null || StringUtils.isBlank(first.getTextContent())) {
            for (Node n : toArray(element.getChildNodes())) {
                if (n.getNodeType() == Node.TEXT_NODE) {
                    sb.append(n.getTextContent());
                } else if (n.getNodeType() == Node.CDATA_SECTION_NODE) {
                    sb.append(((CDATASection) n).getTextContent());
                }
            }
        } else {
            sb.append(first.getTextContent());
        }
        return StringUtils.trimToNull(StringEscapeUtils.unescapeHtml(sb.toString()));
    }

    /**
     * 获得符合类型的第一个节点(单层)
     *
     * @param node
     * @param nodeType
     * @return
     */
    public static Node first(Node node, int... nodeType) {
        if (node == null)
            return null;
        NodeList nds = node.getChildNodes();
        for (int i = 0; i < nds.getLength(); i++) {
            Node child = nds.item(i);
            if (ArrayUtils.contains(nodeType, child.getNodeType())) {
                return child;
            }
        }
        return null;
    }

    private static class EH implements ErrorHandler {
        public void error(SAXParseException x) throws SAXException {
            throw x;
        }

        public void fatalError(SAXParseException x) throws SAXException {
            throw x;
        }

        public void warning(SAXParseException x) throws SAXException {
            throw x;
        }
    }

    /**
     * NodeList转换为数组
     *
     * @param nds
     * @return
     */
    public static Node[] toArray(NodeList nds) {
        if (nds instanceof MyNodeList)
            return ((MyNodeList) nds).list;
        Node[] array = new Node[nds.getLength()];
        for (int i = 0; i < nds.getLength(); i++) {
            array[i] = nds.item(i);
        }
        return array;
    }

    static class MyNodeList implements NodeList {
        Node[] list;

        public int getLength() {
            return list.length;
        }

        public Node item(int index) {
            return list[index];
        }

        public MyNodeList(Node[] list) {
            this.list = list;
        }

        public MyNodeList(List<? extends Node> list) {
            this.list = list.toArray(new Node[list.size()]);
        }
    }
}

