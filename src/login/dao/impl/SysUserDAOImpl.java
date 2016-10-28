package login.dao.impl;

import login.dao.SysUserDAO;
import login.model.AcSysUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysUserDAOImpl implements SysUserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	  
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<AcSysUser> findUsers(AcSysUser user) {
		return getCurrentSession().createQuery("from AcSysUser where userNo = '"+user.getUserNo()+"'").list();
	}

}
