/**
 * 
 */
package fr.qra.myProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.qra.myProject.DAO.ScenarioDAO;
import fr.qra.myProject.Model.Scenario;

/**
 * @author quentin
 *
 */
@Service
@Transactional
public class ScenarioServiceImpl implements ScenarioService {

	@Autowired
	private ScenarioDAO scenarioDAO;
	
	@Override
	public Scenario updateScenario(Scenario scenario) {
		return scenarioDAO.updateScenario(scenario);
	}

	@Override
	public void addScenario(Scenario scenario) {
		scenarioDAO.addScenario(scenario);
	}

	@Override
	public void removeScenario(long id) {
		scenarioDAO.removeScenario(id);
	}

	@Override
	public Scenario getScenario(long id) {
		return scenarioDAO.getScenario(id);
	}

	@Override
	public List<Scenario> listScenario() {
		return scenarioDAO.listScenario();
	}

}
