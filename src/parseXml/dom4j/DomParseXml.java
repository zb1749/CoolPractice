package parseXml.dom4j;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import io.file.FileUtil;
import parseXml.dom4j.pojo.ContentOfflineInfoPojo;
import parseXml.dom4j.pojo.ContentOfflinePojo;

import java.util.List;

/**
 * Created by Kevin on 2016/11/7.
 */
public class DomParseXml {
    public static void main(String[] args) {
        String xml = FileUtil.readFile("D:/IdeaProject/CoolPractice/src/parseXml/dom4j/xml/ShelfContentOfflineRequest.xml");
        System.out.println("input xml: " + xml);
        ContentOfflinePojo pojo = DomParseXml.xmlToPojo(xml);
        System.out.println(pojo.getContentOfflineList().get(0).getContentId());
        System.out.println(pojo.getContentOfflineList().get(0).getChapterId());


    }

    public static ContentOfflinePojo xmlToPojo(String xml) {
        XStream xstream = new XStream(new DomDriver());
        // 类名称转换
        xstream.alias("Request", ContentOfflinePojo.class);
        xstream.alias("contentOfflineList", List.class);
        xstream.alias("contentOfflineInfo", ContentOfflineInfoPojo.class);
        return (ContentOfflinePojo) xstream.fromXML(xml);
    }
}
