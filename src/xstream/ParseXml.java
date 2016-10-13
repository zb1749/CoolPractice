package xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import xstream.pojo.*;

import java.util.List;

public class ParseXml {
    public static void main(String[] args) {
        String response = ReadFile.readXml("D:/IdeaProject/CoolPractice/src/xstream/xml/RespXml_Added.xml");
        System.out.println("input xml: "+response);
        QueryAutoPlayRsp rsp = xmlToQueryPlayRsp(response);
        System.out.println(rsp);
    }

    private static QueryAutoPlayRsp xmlToQueryPlayRsp(String xml) {
        XStream xstream = new XStream(new DomDriver());
        // 类名称转换
        xstream.alias("Response", QueryAutoPlayRsp.class);
        xstream.alias("ResBusiness", ResBusiness.class);
        xstream.alias("Poms", List.class);
        xstream.alias("Product", Product.class);
        xstream.alias("SecondClassifys", List.class);
        xstream.alias("SecondClassify", SecondClassify.class);
        xstream.alias("MediaFiles", List.class);
        xstream.alias("MediaFile", MediaFile.class);
        xstream.alias("DisplayFilePath", List.class);
        xstream.alias("DisplayFile", DisplayFile.class);
        //忽略字段
        xstream.omitField(Product.class, "UpdateFields");
        xstream.omitField(Product.class, "Priority");
        // 变量名称大小写转换
        xstream.aliasField("ProcessId", QueryAutoPlayRsp.class, "processId");
        xstream.aliasField("ResCode", QueryAutoPlayRsp.class, "resCode");
        xstream.aliasField("ResMessage", QueryAutoPlayRsp.class,"resMessage");
        xstream.aliasField("ResBusiness", QueryAutoPlayRsp.class,"resBusiness");
        xstream.aliasField("Poms", ResBusiness.class, "poms");
        xstream.aliasField("PrdContId", Product.class, "prdContId");
        xstream.aliasField("PrdInfoId", Product.class, "prdInfoId");
        xstream.aliasField("PrdInfoName", Product.class, "prdInfoName");
        xstream.aliasField("BcStatus", Product.class, "bcStatus");
        xstream.aliasField("BcPerson", Product.class, "bcPerson");
        xstream.aliasField("BcTime", Product.class, "bcTime");
        xstream.aliasField("BcRefuseReason", Product.class,"bcRefuseReason");
        xstream.aliasField("PubStatus", Product.class, "pubStatus");
        xstream.aliasField("RepealStatus", Product.class, "repealStatus");
        xstream.aliasField("ContentId", Product.class, "contentId");
        xstream.aliasField("AssetId", Product.class, "assetId");
        xstream.aliasField("ContentName", Product.class, "contentName");
        xstream.aliasField("ShortName", Product.class, "shortName");
        xstream.aliasField("Detail", Product.class, "detail");
        xstream.aliasField("Cduration", Product.class, "cduration");
        xstream.aliasField("BcId", Product.class, "bcId");
        xstream.aliasField("CpId", Product.class, "cpId");
        xstream.aliasField("NcpId", Product.class, "ncpId");
        xstream.aliasField("CopyrightCpid", Product.class, "copyrightCpid");
        xstream.aliasField("PrimaryKeywords", Product.class,"primaryKeywords");
        xstream.aliasField("Keywords", Product.class, "keywords");
        xstream.aliasField("FeeType", Product.class, "feeType");
        xstream.aliasField("CreateTime", Product.class, "createTime");
        xstream.aliasField("Status", Product.class, "status");
        xstream.aliasField("ContentType", Product.class, "contentType");
        xstream.aliasField("Category", Product.class, "category");
        xstream.aliasField("FormType", Product.class, "formType");
        xstream.aliasField("Labels", Product.class, "labels");
        xstream.aliasField("CreatorId", Product.class, "creatorId");
        xstream.aliasField("DirectrecFlag", Product.class, "directrecFlag");
        xstream.aliasField("MediaLevel", Product.class, "mediaLevel");
        xstream.aliasField("Author", Product.class, "author");
        xstream.aliasField("SerialCount", Product.class, "serialCount");
        xstream.aliasField("SerialSequence", Product.class,"serialSequence");
        xstream.aliasField("SerialContentID", Product.class,"serialContentID");
        xstream.aliasField("LastModifytime", Product.class,"lastModifytime");
        xstream.aliasField("WaterMask", Product.class, "waterMask");
        xstream.aliasField("Assist", Product.class, "assist");
        xstream.aliasField("DisplayType", Product.class, "displayType");
        xstream.aliasField("DisplayName", Product.class, "displayName");
        xstream.aliasField("Udid", Product.class, "udid");
        xstream.aliasField("SecondClassifys", Product.class,"secondClassifys");
        xstream.aliasField("MediaFiles", Product.class, "mediaFiles");
        xstream.aliasField("DisplayFile", Product.class, "displayFile");
        xstream.aliasField("ClassifyKey", SecondClassify.class,"classifyKey");
        xstream.aliasField("ClassifyValue", SecondClassify.class,"classifyValue");
        xstream.aliasField("MediaFileId", MediaFile.class, "mediaFileId");
        xstream.aliasField("MediaFileName", MediaFile.class,"mediaFileName");
        xstream.aliasField("MediaCodeRate", MediaFile.class,"mediaCodeRate");
        xstream.aliasField("MediaType", MediaFile.class, "mediaType");
        xstream.aliasField("MediaResolution", MediaFile.class,"mediaResolution");
        xstream.aliasField("MediaFilePreviewPath", MediaFile.class,"mediaFilePreviewPath");
        xstream.aliasField("MediaUsageCode", MediaFile.class,"mediaUsageCode");
        xstream.aliasField("DisplayFilePath", DisplayFile.class,"displayFilePath");

        return (QueryAutoPlayRsp) xstream.fromXML(xml);
    }

}
