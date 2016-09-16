package struts2.pojo;

/**
 * Created by kevin on 2016/9/16.
 */
public class AjaxPojo {
    private String id;
    private String name;
    private String num;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "AjaxPojo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
