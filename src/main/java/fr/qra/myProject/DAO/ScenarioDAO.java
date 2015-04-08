package fr.qra.myProject.DAO;

import java.util.List;

import fr.qra.myProject.Model.Scenario;

public interface ScenarioDAO {

	public Scenario updateScenario(Scenario scenario);

	public long addScenario(Scenario scenario);

	public void removeScenario(long id);

	public Scenario getScenario(long id);

	public List<Scenario> listScenario();
}
