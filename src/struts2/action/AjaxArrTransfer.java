package struts2.action;

import lombok.Getter;
import lombok.Setter;
import struts2.pojo.AjaxPojo;

import java.util.List;

/**
 * Created by kevin on 2016/9/16.
 */
public class AjaxArrTransfer {
    @Getter
    @Setter
    private List<AjaxPojo> ajaxList;

    public void transArr() {
        if (ajaxList != null && !ajaxList.isEmpty()) {
            for (AjaxPojo pojo : ajaxList) {
                System.out.println(pojo.toString());
            }
        }else{
            System.out.println("Do not receive ajaxList!");
        }
    }

}
