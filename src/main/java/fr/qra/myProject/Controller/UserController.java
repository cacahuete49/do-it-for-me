package fr.qra.myProject.Controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.NonUniqueObjectException;
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
@SessionAttributes(value = "user", types = { User.class })
public class UserController {

	public final static String INCORRECT_PASSWORD = "Le mot de passe est incorrect";
	public final static String INCORRECT_EMAIL = "L'email est incorrect";
	public final static String INVALID_PASSWORD = "8 charactères alphanumériques minimum";

	/**
	 * Permet la visualisation de létat de l'user en session
	 * 
	 * @param session
	 */
	@ModelAttribute
	public void showSession(HttpSession session) {
		System.out.println("UserController.showSession()");
		User u = (User) session.getAttribute("user");

		if (u != null)
			System.err.println("UserSession = " + u.toString());
		else
			System.err.println("UserSession = null");
	}

	@Autowired
	private UserService userService;

	@Autowired
	private ScenarioService scenarioService;

	/*** PUBLIC ***/

	@RequestMapping(method = RequestMethod.GET, value = { "/", "/accueil" })
	public ModelAndView home(Locale locale, Model model) {
		System.out.println("Welcome home!");
		ModelAndView view = new ModelAndView("home");
		User user = null;
		if (model.containsAttribute("user")) {
			user = (User) model.asMap().get("user");
		} else {
			user = new User();
		}
		view.addObject("user", user);
		return view;
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

	private boolean isVerifyPassword(String password) {
		return (password.matches("^[A-Za-z0-9]{8,}"));
	}

	private boolean isCorrectEmail(String password) {
		return (password
				.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"));
	}

	@RequestMapping("createUser")
	public ModelAndView createUser(@ModelAttribute User user) {
//		if (user!=null && user.getId()==0)
//			return new ModelAndView("error/403");
				
		System.out.println("je crée le user");
		// test du password
		if (!isCorrectEmail(user.getEmail())) {
			ModelAndView model = new ModelAndView("inscription");
			model.addObject("error_signin", INCORRECT_EMAIL);
			return model;
		}

		if (user.getPassword().equals(user.getConfirmPassword())) {
			if (!isVerifyPassword(user.getPassword())) {
				ModelAndView model = new ModelAndView("inscription");
				model.addObject("error_signin", INVALID_PASSWORD);
				return model;
			}
			String hashPassword = encodePasswordWithBCrypt(user.getPassword());
			user.setPassword(hashPassword);
			user.setConfirmPassword(hashPassword);
			try {
				userService.addUser(user);
			} catch (Exception e) {
				// email déja utilisé
				ModelAndView model = new ModelAndView("inscription");
				model.addObject("error_signin", "Email déjà utilisé");
				return model;
			}
		} else {
			ModelAndView model = new ModelAndView("inscription");
			model.addObject("error_signin", INCORRECT_PASSWORD);
			return model;
		}
		user = userService.getUser(user.getEmail());
		user.setPassword("");
		return new ModelAndView("privee/coordonnees", "user", user);
	}

	@RequestMapping(value = { "/connectUser", "connectUser" })
	public ModelAndView connectUser(@ModelAttribute User user) {
		System.out.println("je connect le user, " + user);
		User userBDD = null;
		// recup user via email
		try {
			userBDD = userService.getUser(user.getEmail());
			if (userBDD == null)
				throw new NullPointerException("failed to find");
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			ModelAndView model = new ModelAndView("home");
			model.addObject("error", INCORRECT_EMAIL);
			return model;
		}

		// test mdp
		if (new BCryptPasswordEncoder().matches(user.getPassword(), userBDD.getPassword())) {
			userBDD.setPassword("");
			ModelAndView model = new ModelAndView("privee/coordonnees", "user", userBDD);
			return model;
		} else {
			ModelAndView model = new ModelAndView("home");
			model.addObject("error", INCORRECT_PASSWORD);
			return model;
		}
	}

	/*** FIN PUBLIC ***/

	/*** PRIVEE ***/

	@RequestMapping(method = RequestMethod.GET, value = { "/editUser" })
	public String modifierUser(@ModelAttribute("user") User user) {
		if (user == null || user.getId() == 0)
			return "error/403";
		return "privee/editUser";
	}

	@RequestMapping(method = RequestMethod.GET, value = { "/coordonnees" })
	public String seeUser(@ModelAttribute("user") User user) {
		if (user == null || user.getId() == 0)
			return "error/403";
		return "privee/coordonnees";
	}

	@RequestMapping(value = { "mesScenarii" })
	public ModelAndView mesScenarii(@ModelAttribute("user") User user, @ModelAttribute Scenario scenario) {
		System.out.println("j'accede aux scenarii de l'user");

		if (user == null || user.getId() == 0)
			return new ModelAndView("error/403");

		User userBDD = userService.getUser(user.getEmail());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", userBDD);
		modelAndView.addObject("scenario", new Scenario());
		modelAndView.setViewName("privee/listeScenarii");
		return modelAndView;
	}

	@RequestMapping(value = { "editUser" })
	public ModelAndView editUser(@ModelAttribute("user") User user) {
		System.out.println("UserController.editUser()");
		User userBDD = null;
		// si le user n'est pas connecté
		if (user == null || user.getId() == 0)
			return new ModelAndView("error/403");
		// si le user est connecté sans être en base
		else if ((userBDD = userService.getUserById(user.getId())) == null)
			return new ModelAndView("error/403");
		
		if (user.getEmail().isEmpty()) {
			user.setEmail(userBDD.getEmail());
		} else if (!isCorrectEmail(user.getEmail())) {
			ModelAndView model = new ModelAndView("privee/editUser");
			model.addObject("error", INCORRECT_EMAIL);
			return model;
		}

		if (user.getNom().isEmpty())
			user.setNom(userBDD.getNom());
		if (user.getPrenom().isEmpty())
			user.setPrenom(userBDD.getPrenom());
		if (user.getPassword().isEmpty()) {
			user.setPassword(userBDD.getPassword());
		} else if (!user.getPassword().equals(user.getConfirmPassword()) || !isVerifyPassword(user.getPassword())) {
			ModelAndView model = new ModelAndView("privee/editUser");
			model.addObject("error", INCORRECT_PASSWORD);
			return model;
		} else {
			user.setPassword(encodePasswordWithBCrypt(user.getPassword()));
		}
		
		userService.updateUser(user);
		user.setPassword("");
		return new ModelAndView("privee/coordonnees");
	}

	// actually unused
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(@ModelAttribute User user, SessionStatus sessionStatus) {
		System.out.println("UserController.deleteUser()");

		if (user == null || user.getId() == 0)
			return new ModelAndView("error/403");

		try {
			userService.removeUser(user.getEmail());
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
	public ModelAndView achat(@ModelAttribute Scenario scenario, @ModelAttribute("user") User user) {
		System.out.println("UserController.achat()");

		if (user == null || user.getId() == 0)
			return new ModelAndView("error/403");

		boolean estDejaPresent = false;
		List<Scenario> scenarii = scenarioService.listScenario();
		for (Scenario s : scenarii)
			if (scenario.getId() == s.getId()) {
				scenario = s;
				break;
			}
		// scenario = scenarioService.getScenario(scenario.getId());
		user = userService.getUser(user.getEmail());
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

	/*** FIN PRIVEE ***/

}
