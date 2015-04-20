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
@SessionAttributes(value = "user")
public class ScenariiController {

	@Autowired
	private UserService userService;

	@Autowired
	private ScenarioService scenarioService;
	
	/***		PUBLIC		***/

	@RequestMapping(method = RequestMethod.GET, value = { "/boutique" })
	public ModelAndView boutique(Locale locale, Model model) {

		model.addAttribute("scenario", new Scenario());

		List<Scenario> scenariiList = scenarioService.listScenario();
		return new ModelAndView("boutique", "scenariiList", scenariiList);
	}
	
	/***		FIN PUBLIC		***/
	
	/***		PRIVEE		***/

	@RequestMapping(value = { "note", "/note" })
	public String note(@ModelAttribute Scenario scenario, HttpSession httpSession) {
		System.out.println("note de l'utilisateur");
		
		User user = (User) httpSession.getAttribute("user");
		
		if (user==null || user.getId()==0)
			return "error/403";
		
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
	
	/***		FIN PRIVEE		***/
}
