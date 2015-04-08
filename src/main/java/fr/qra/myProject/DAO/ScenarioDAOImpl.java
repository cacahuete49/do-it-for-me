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

import fr.qra.myProject.Model.Scenario;

/**
 * @author quentin
 *
 */
@Repository
public class ScenarioDAOImpl implements ScenarioDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Scenario updateScenario(Scenario scenario) {
		sessionFactory.getCurrentSession().update(scenario);
		return scenario;
	}

	@Override
	public long addScenario(Scenario scenario) {
		Serializable id = sessionFactory.getCurrentSession().save(scenario);
		return (Long) id;
	}

	@Override
	public void removeScenario(long id) {
		Scenario scenario = new Scenario();
		scenario.setId(id);
		sessionFactory.getCurrentSession().delete(scenario);
	}

	@Override
	public Scenario getScenario(long id) {
		return (Scenario) sessionFactory.getCurrentSession().get(Scenario.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Scenario> listScenario() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT s FROM Scenario s");
		return query.list();
	}

}
