package login.dao.impl;

import login.dao.UserDAO;
import login.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl  implements UserDAO{
	@Autowired
	private SessionFactory sessionFactory;
	  
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;  
    }  
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addUser(User user) {
		getCurrentSession().save(user);
	}

	public void delUser(Long id) {
		sessionFactory.getCurrentSession().delete(getUser(id));
	}

	public User getUser(Long id) {
		return 	(User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<User> listAllUser() {
		return 	sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public void updateUser(User user) {
	}

}
