package login.spring_security.model;

public class LoginInfo {
	private String msg = "";
	private boolean ok = false;
	private AcSysUser user;

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public AcSysUser getUser() {
		return user;
	}
	public void setUser(AcSysUser user) {
		this.user = user;
	}

}
