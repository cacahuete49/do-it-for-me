package fr.qra.myProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.qra.myProject.DAO.UserHasScenarioDAO;
import fr.qra.myProject.Model.UserHasScenario;

@Service
@Transactional
public class UserHasScenarioServiceImpl implements UserHasScenarioService {

	@Autowired
	private UserHasScenarioDAO userHasScenarioDAO;

	@Override
	public List<UserHasScenario> listUserHasScenario() {
		return userHasScenarioDAO.listUserHasScenario();
	}
}
