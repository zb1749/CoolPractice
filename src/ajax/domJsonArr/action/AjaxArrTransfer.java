package ajax.domJsonArr.action;

import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import struts2.pojo.AjaxPojo;

import java.util.List;

/**
 * Created by kevin on 2016/9/16.
 */
public class AjaxArrTransfer extends ActionSupport{
    @Getter
    @Setter
    private List<AjaxPojo> ajaxList;
    @Getter
    @Setter
    private String hid;

    public String transArr() {
        System.out.println("hid: " + hid);
        if (ajaxList != null && !ajaxList.isEmpty()) {
            for (AjaxPojo pojo : ajaxList) {
                System.out.println(pojo.toString());
            }
        } else {
            System.out.println("Do not receive ajaxList!");
        }
        return "trans";
    }

    public String addDialog(){
        return "add";
    }

}
