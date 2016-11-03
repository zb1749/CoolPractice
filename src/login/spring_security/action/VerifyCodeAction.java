package login.spring_security.action;

import com.opensymphony.xwork2.ActionSupport;
import login.spring_security.util.VerifyCodeUtil;
import org.apache.struts2.interceptor.SessionAware;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * 图形验证码
 */
public class VerifyCodeAction extends ActionSupport implements SessionAware {

    private Map session;

    //图片流
    private ByteArrayInputStream imageStream;
    //必须有get否则struts2访问不了这个imageStream
    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String execute() throws Exception {
        //获取验证码
        String verifyCode = VerifyCodeUtil.getCheckCodeStr();
        imageStream = VerifyCodeUtil.getVerifyCodeInputStream(verifyCode);
        System.out.println("verifyCode: " + verifyCode);
        //放入session中
        session.put("imgValidationCode", verifyCode);
        return SUCCESS;
    }

}
