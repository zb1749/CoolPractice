package login.spring_security.service.impl;

import login.spring_security.dao.UserDAO;
import login.spring_security.model.User;
import login.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=false)
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDao;

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void delUser(Long id) {
		userDao.delUser(id);
	}

	public User getUser(Long id) {
		return userDao.getUser(id);
	}

	public List<User> listAllUser() {
		return userDao.listAllUser();
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

}
