package login.service.impl;

import login.dao.UserDAO;
import login.model.User;
import login.service.UserService;
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
