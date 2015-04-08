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

import fr.qra.myProject.Model.Message;

/**
 * @author quentin
 *
 */
@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public long addMessage(Message message) {
		Serializable id = sessionFactory.getCurrentSession().save(message);
		return (Long) id;
	}

	@Override
	public void removeMessage(long id) {
		Message message = getMessage(id);
		sessionFactory.getCurrentSession().delete(message);

	}

	@Override
	public Message getMessage(long id) {
		return (Message) sessionFactory.getCurrentSession().get(Message.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Message> listMessage() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT m FROM Message m");
		return query.list();
	}

}
