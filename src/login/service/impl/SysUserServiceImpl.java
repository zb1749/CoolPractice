package login.service.impl;

import login.dao.SysUserDAO;
import login.model.AcSysUser;
import login.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	SysUserDAO sysUserDAO;

	public List<AcSysUser> findUsers(AcSysUser user) {
		return sysUserDAO.findUsers(user);
	}

}
