package ajax.domJsonArr.action;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import ajax.domJsonArr.pojo.AjaxPojo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2016/9/16.
 */
public class AjaxArrTransfer extends ActionSupport{

//    @Getter
//    @Setter
//    private String hid;
    @Getter
    @Setter
    private String ajaxArrJson;
    @Getter
    @Setter
    private String jsonResult;

    public String transArr() {
        List<AjaxPojo> ajaxList = new ArrayList<AjaxPojo>();
        if (!StringUtils.isEmpty(ajaxArrJson)) {
            ajaxList = JSONArray.parseArray(ajaxArrJson, AjaxPojo.class);
        }
        //System.out.println("hid: " + hid);
        if (ajaxList != null && !ajaxList.isEmpty()) {
            for (AjaxPojo pojo : ajaxList) {
                System.out.println(pojo.toString());
            }
        } else {
            System.out.println("Do not receive ajaxList!");
        }
        //return "trans";
        jsonResult = ajaxArrJson;
        return "trans";
    }

    public String addDialog(){
        return "add";
    }

}
