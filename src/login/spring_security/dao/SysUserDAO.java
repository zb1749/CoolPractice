package login.spring_security.dao;

import login.spring_security.model.AcSysUser;

import java.util.List;


public interface SysUserDAO {

	List<AcSysUser> findUsers(AcSysUser user);
}
