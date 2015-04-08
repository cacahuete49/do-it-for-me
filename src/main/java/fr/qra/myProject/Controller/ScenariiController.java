/**
 * 
 */
package fr.qra.myProject.Controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.qra.myProject.Model.Scenario;
import fr.qra.myProject.Model.User;
import fr.qra.myProject.Model.UserHasScenario;
import fr.qra.myProject.Service.ScenarioService;
import fr.qra.myProject.Service.UserService;

/**
 * @author quentin
 *
 */
@Controller
@SessionAttributes(value = "user", types = { User.class })
public class ScenariiController {

	@Autowired
	private UserService userService;

	@Autowired
	private ScenarioService scenarioService;

	@RequestMapping(method = RequestMethod.GET, value = { "/boutique" })
	public ModelAndView boutique(Locale locale, Model model, HttpSession session) {

		User user = (User) session.getAttribute("user");
		if (user == null) {
			user = new User();
		}
		
		model.addAttribute("user", user);
		model.addAttribute("scenario", new Scenario());
		// for (Scenario s : scenarioService.listScenario() ) {
		// int result=0;
		// int nbVotant=0;
		// for (UserHasScenario u : s.getListeUser() ){
		// result+=u.getNoteUser();
		// if (u.getNoteUser()!=-1) nbVotant++;
		// }
		// if (nbVotant!=0)
		// s.setNote(result/nbVotant);
		// scenarioService.updateScenario(s);
		// }

		List<Scenario> scenariiList = scenarioService.listScenario();
		return new ModelAndView("boutique", "scenariiList", scenariiList);
	}

	@RequestMapping(value = { "note", "/note" })
	public String note(@ModelAttribute Scenario scenario, HttpSession session) {
		System.out.println("note de l'utilisateur");
		User user = (User) session.getAttribute("user");
		for (UserHasScenario uhs : user.getMesScenarii())
			if (uhs.getScenario().getId() == scenario.getId()) {
				uhs.setNoteUser(scenario.getNote());
				scenario = uhs.getScenario();

				int result = 0;
				int nbVotant = 0;
				for (UserHasScenario u : scenario.getListeUser()) {
					result += u.getNoteUser();
					if (u.getNoteUser() != -1)
						nbVotant++;
				}
				if (nbVotant != 0)
					scenario.setNote(result / nbVotant);

				scenarioService.updateScenario(scenario);
				break;
			}
		userService.updateUser(user);

		return "redirect:/mesScenarii";
	}
}
