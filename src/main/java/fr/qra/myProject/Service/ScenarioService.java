package fr.qra.myProject.Service;

import java.util.List;

import fr.qra.myProject.Model.Scenario;

public interface ScenarioService {

	public Scenario updateScenario(Scenario scenario);

	public void addScenario(Scenario scenario);

	public void removeScenario(long id);

	public Scenario getScenario(long id);

	public List<Scenario> listScenario();
}
