package fr.qra.myProject.Controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import fr.qra.myProject.Model.Scenario;
import fr.qra.myProject.Model.User;
import fr.qra.myProject.Model.UserHasScenario;
import fr.qra.myProject.Service.ScenarioService;
import fr.qra.myProject.Service.UserService;

@Controller
@SessionAttributes(value = "userSession", types = { User.class })
public class UserController {

	public final static String INCORRECT_PASSWORD = "Le mot de passe est incorrect";
	public final static String INCORRECT_EMAIL = "L'email est incorrect";

	@Autowired
	private UserService userService;

	@Autowired
	private ScenarioService scenarioService;

	@ModelAttribute("user")
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ModelAndView home(Locale locale, Model model, HttpSession session) {
		System.out.println("Welcome home!");
		User user = (User) session.getAttribute("user");
		if (user == null) {
			user = new User();
		}
		model.addAttribute("user", user);
		System.out.println("user=" + user.toString());
		return new ModelAndView("home", "user", user);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/inscription")
	public String inscription(Locale locale, Model model) {
		System.out.println("Inscription home! The client locale is {}.");
		if (model.containsAttribute("user"))
			System.out.println("utilisateur déjà présent");
		else
			model.addAttribute("user", new User());
		return "inscription";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/editUser" })
	public String modifierUser() {
		return "privee/editUser";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/coordonnees" })
	public String seeUser() {
		return "privee/coordonnees";
	}

	@RequestMapping(value = { "mesScenarii" })
	public ModelAndView mesScenarii(@ModelAttribute User user,
			@ModelAttribute Scenario scenario, Model model) {
		System.out.println("j'accede aux scenarii de l'user");
		User userBDD = userService.getUserByEmail(user.getEmail());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", userBDD);
		modelAndView.addObject("scenario", new Scenario());
		modelAndView.setViewName("privee/listeScenarii");
		return modelAndView;
	}

	@ModelAttribute("user")
	@RequestMapping("createUser")
	public ModelAndView createUser(@ModelAttribute User user){
		System.out.println("je crée le user");
		//test du password
		if (user.getPassword().equals(user.getConfirmPassword())) {
			String hashPassword = encodePasswordWithBCrypt(user.getPassword());
			user.setPassword(hashPassword);
			user.setConfirmPassword(hashPassword);
			try {
				userService.addUser(user);
			} catch (ConstraintViolationException e ) {
				//email déja utilisé
				ModelAndView model = new ModelAndView("inscription");
				model.addObject("error", "Email déjà utilisé");
				return model;
			}
		} else {
			ModelAndView model = new ModelAndView("inscription");
			model.addObject("error", INCORRECT_PASSWORD);
			return model;
		}
		return new ModelAndView("privee/coordonnees", "user", user);
	}

	@RequestMapping(value = { "connectUser" })
	public ModelAndView connectUser(@ModelAttribute User user) {
		System.out.println("je connect le user, " + user);
		User userBDD = null;
		// recup user via email
		try {
			userBDD = userService.getUserByEmail(user.getEmail());
		} catch (NullPointerException e) {
			ModelAndView model = new ModelAndView("home");
			model.addObject("error", INCORRECT_EMAIL);
			return model;
		}

		// test mdp
		if (new BCryptPasswordEncoder()
				.matches(user.getPassword(), userBDD.getPassword())) {
			ModelAndView model = new ModelAndView("privee/coordonnees", "user", userBDD);
			return model;
		} else {
			ModelAndView model = new ModelAndView("home");
			model.addObject("error", INCORRECT_PASSWORD);
			return model;
		}
	}

	@RequestMapping(value = { "editUser" })
	public ModelAndView editUser(@ModelAttribute User user) {
		System.out.println("UserController.editUser()");
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			ModelAndView model = new ModelAndView("privee/editUser");
			model.addObject("error", INCORRECT_PASSWORD);
			return model;
		}
		User userBDD = userService.getUser(user.getId());
		if (user.getEmail() == "")
			user.setEmail(userBDD.getEmail());
		if (user.getNom() == "")
			user.setNom(userBDD.getNom());
		if (user.getPrenom() == "")
			user.setPrenom(userBDD.getPrenom());
		if (user.getPassword() == "")
			user.setPassword(userBDD.getPassword());
		user.setPassword(encodePasswordWithBCrypt(user.getPassword()));
		userService.updateUser(user);
		return new ModelAndView("privee/coordonnees");
	}

	// actually unused
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(@ModelAttribute User user, SessionStatus sessionStatus) {
		System.out.println("UserController.deleteUser()");
		try {
			userService.removeUser(user.getId());
		} catch (Exception e) {
			System.out.println("suppression de l'utilisateur failed");
		} finally {
			sessionStatus.setComplete();
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}

	@RequestMapping(value = { "disconnect", "/deconnexion" })
	public String disconnectUser(SessionStatus sessionStatus) {
		System.out.println("déconnexion de l'utilisateur");
		sessionStatus.setComplete();
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/achat")
	public ModelAndView achat(@ModelAttribute Scenario scenario, @ModelAttribute User user) {
		System.out.println("UserController.achat()");
		boolean estDejaPresent = false;
		List<Scenario> scenarii = scenarioService.listScenario();
		for (Scenario s : scenarii)
			if (scenario.getId() == s.getId()) {
				scenario = s;
				break;
			}
//		scenario = scenarioService.getScenario(scenario.getId());
		user = userService.getUserByEmail(user.getEmail());
		ModelAndView modelAndView = new ModelAndView();
		// je vérifi si je possède déjà ce scenario
		for (UserHasScenario s : user.getMesScenarii())
			if (s.getScenario().getId() == scenario.getId())
				estDejaPresent = true;

		// si je ne possède pas ce scenario
		if (!estDejaPresent) {

			UserHasScenario achat = new UserHasScenario();

			achat.setUser(user);
			achat.setScenario(scenario);
			achat.setPrixAchat(scenario.getPrix());
			achat.setCreatedDate(new Date(System.currentTimeMillis()));
			achat.setNoteUser(-1);
			user.getMesScenarii().add(achat);
		}
		try {
			if (!estDejaPresent) {
				user = userService.updateUser(user);
				scenario.setNbTelechargement(scenario.getNbTelechargement() + 1);
				scenarioService.updateScenario(scenario);
			}
			modelAndView.addObject("user", user);
		} catch (NonUniqueObjectException e) {
			System.out.println("déjà présent");
			modelAndView.setViewName("redirect:/boutique");
			return modelAndView;
		} catch (Exception e) {
			System.out.println("je sais pas pourquoi je tombe là");
		}
		modelAndView.setViewName("redirect:mesScenarii");
		return modelAndView;
	}

	public static String encodePasswordWithBCrypt(String plainPassword) {
		return new BCryptPasswordEncoder().encode(plainPassword);
	}

}
