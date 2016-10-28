package login.service;

import login.model.AcSysUser;

import java.util.List;

public interface SysUserService {
	List<AcSysUser> findUsers(AcSysUser user);

}
