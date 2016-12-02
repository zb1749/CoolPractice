package parseXml.xstream.annotation;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import io.file.FileUtil;
import parseXml.xstream.annotation.pojo.BatchBindCardResponse;


public class ParseXml {
    public static void main(String[] args) {
        String response = FileUtil.readFile("D:/IdeaProject/CoolPractice/src/parseXml/xstream/annotation/xml/RespXml.xml");
        System.out.println("input xml: " + response);
        BatchBindCardResponse rsp = xmlToQueryPlayRsp(response);
        System.out.println(rsp);

//        BatchBindCardResponse retData = new BatchBindCardResponse();
//        retData.setBindTime("2016");
//        RetDataPojo pojo = new RetDataPojo();
//        RetEnjoyBindData bindData = new RetEnjoyBindData();
//        bindData.setCardId("card");
//        bindData.setMobile("123");
//        bindData.setRetCode("00");
//        List<RetEnjoyBindData> bindDataList = new ArrayList<RetEnjoyBindData>();
//        bindDataList.add(bindData);
//        pojo.setRetEnjoyBindData(bindDataList);
//        retData.setRetData(pojo);
//        XStream xstream = new XStream();
//        xstream.alias("Response", BatchBindCardResponse.class);
//        String respXml = xstream.toXML(retData);
//        System.out.println(respXml);


    }

    private static BatchBindCardResponse xmlToQueryPlayRsp(String xml) {

        // 解析返回XML
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(BatchBindCardResponse.class);
        BatchBindCardResponse retData = (BatchBindCardResponse) xstream.fromXML(xml);
        return retData;
    }

}
