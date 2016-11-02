package login.spring_security.controller;

import login.spring_security.util.VerifyCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图形验证码
 */
@Controller
@RequestMapping("/mvc")
public class VerifyController {
	
	
	@RequestMapping("/index")
	public String indexAction(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("images/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String code = VerifyCodeUtil.getCheckCodeStr();
		int verifyResult = VerifyCodeUtil.getCheckCodeResult(code);
		request.getSession().setAttribute("verifyResult", verifyResult);
		try {
			ImageIO.write(VerifyCodeUtil.getVerifyCode(code) , "JPEG",response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
