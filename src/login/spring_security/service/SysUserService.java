package login.spring_security.service;

import login.spring_security.model.AcSysUser;

import java.util.List;

public interface SysUserService {
	List<AcSysUser> findUsers(AcSysUser user);

}
