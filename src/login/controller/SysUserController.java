package login.controller;

import login.model.AcSysUser;
import login.model.LoginInfo;
import login.service.SysUserService;
import login.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class SysUserController {
	@Autowired
	private SysUserService cwSysUserService;
	
	@RequestMapping(value="/login")
	public String login(@RequestParam String userNo, @RequestParam String password, @RequestParam String verifyCode, HttpServletRequest request, Errors error){
		String verifyResult = request.getSession().getAttribute("verifyResult").toString();
		LoginInfo loginInfo = new LoginInfo();
		//1验证码错误
		if(verifyCode==null || !verifyResult.equals(verifyCode)){
			loginInfo.setMsg("验证码错误！");
			request.setAttribute("loginInfo", loginInfo);
			   error.rejectValue("msg","鸭掌门错误");
		//return "/front/login";
			return null;
		}
		
		AcSysUser user = new AcSysUser();
		user.setUserNo(userNo);
		List<AcSysUser> userList = cwSysUserService.findUsers(user);
		//2用户不存在
		if(userList == null || userList.size()==0 ){
			return "fail";
		}
		//3密码不正确
		if(!userList.get(0).getPassword().equals(EncryptUtil.getMD5(password))){
			return "fail";
		}
		
		//4查找用户角色
		
		
		//5记录登录信息
		
		//
		return "/success";
	}

}
