package fr.qra.myProject.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.qra.myProject.Model.UserHasScenario;

@Repository
public class UserHasScenarioDAOImpl implements UserHasScenarioDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserHasScenario> listUserHasScenario(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT uhs FROM UserHasScenario uhs");
		return query.list();
	}
}
