package login.dao;

import login.model.User;

import java.util.List;

public interface UserDAO {
	 public User getUser(Long id);
	 public List<User> listAllUser();
	 public void addUser(User user);
	 public void delUser(Long id);
	 public void updateUser(User user);
	 
}
