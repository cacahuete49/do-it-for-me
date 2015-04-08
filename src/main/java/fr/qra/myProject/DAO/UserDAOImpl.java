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
	public User getUserByEmail(String email) throws NullPointerException{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT u FROM User u WHERE email = :email");
		query.setParameter("email", email);
		@SuppressWarnings("unchecked")
		List<User> user = query.list();
		return user.get(0);
	}

	@Override
	public long addUser(User user) {
		Serializable id = sessionFactory.getCurrentSession().save(user);
		return (Long) id;
	}

	@Override
	public void removeUser(long id) {
		User user = new User();
		user.setId(id);
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public User getUser(long id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public User updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
		return user;
	}
}
