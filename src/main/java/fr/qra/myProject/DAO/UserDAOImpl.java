/**
 * 
 */
package fr.qra.myProject.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.qra.myProject.Model.User;

/**
 * @author quentin
 *
 */
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUser() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT u FROM User u");
		return query.list();
	}

	@Override
	public String addUser(User user) {
		Serializable id = sessionFactory.getCurrentSession().save(user);
		return (String) id;
	}

	@Override
	public void removeUser(String email) {
		User user = new User();
		user.setEmail(email);
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public User getUser(String email) {
//		return (User) sessionFactory.getCurrentSession().get(User.class, email);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT u FROM User u WHERE email= :email");
		query.setParameter("email", email);
		return (User) query.uniqueResult();
	}
	
	@Override
	public User getUserById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT u FROM User u WHERE id= :id");
		query.setParameter("id", id);
		return (User) query.uniqueResult();
	}

	@Override
	public User updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		return user;
	}
}
