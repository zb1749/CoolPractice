package tabswitch;

import lombok.Getter;
import lombok.Setter;

public class TabSwitch {

    @Getter
    @Setter
    private String pageType;

    public String index(){

        return "index";
    }

    public String innerTable() {
        if ("all".equalsIgnoreCase(pageType)) {


        }else if ("audit".equalsIgnoreCase(pageType)) {


        }
        return "innerTable";
    }

}
