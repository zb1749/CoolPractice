package login.dao;

import login.model.AcSysUser;

import java.util.List;


public interface SysUserDAO {

	List<AcSysUser> findUsers(AcSysUser user);
}
