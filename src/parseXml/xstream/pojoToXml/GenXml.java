package parseXml.xstream.pojoToXml;

import com.thoughtworks.xstream.XStream;
import parseXml.xstream.pojoToXml.pojo.BDCCheckRespFilePojo;
import parseXml.xstream.pojoToXml.pojo.DataConsistencyRespPojo;
import parseXml.xstream.pojoToXml.pojo.SvcContRespPojo;


public class GenXml {
    public static void main(String[] args) {
        //String response = FileUtil.readFile("D:/IdeaProject/CoolPractice/src/parseXml.xstream/xml/5010_Req_201609270078644_012.xml");
        DataConsistencyRespPojo respPojoData = new DataConsistencyRespPojo();
        respPojoData.setRspCode("123resp");
        respPojoData.setRspDesc("123desc");
        respPojoData.setTransactionID("1");

        SvcContRespPojo respPojoCont = new SvcContRespPojo();
        BDCCheckRespFilePojo respPojo = new BDCCheckRespFilePojo();
        respPojo.setSvcCont(respPojoCont);
        respPojoCont.setDataConsistencyResp(respPojoData);

        String respXml = GenXml.pojoToXml(respPojo);
        System.out.println(respXml);
    }

    private static String pojoToXml(BDCCheckRespFilePojo respPojo) {
        XStream xstream = new XStream();
        xstream.alias("BDC", BDCCheckRespFilePojo.class);
        String respXml = xstream.toXML(respPojo);

        return respXml;
    }

}
