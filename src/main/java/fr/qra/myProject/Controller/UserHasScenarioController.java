package fr.qra.myProject.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.qra.myProject.Model.Message;
import fr.qra.myProject.Model.User;
import fr.qra.myProject.Model.UserHasScenario;
import fr.qra.myProject.Service.MessageService;
import fr.qra.myProject.Service.UserService;

@Controller
public class UserHasScenarioController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@RequestMapping(method = RequestMethod.GET, value = "/admin")
	public ModelAndView dashBoard(HttpSession httpSession) {
		ModelAndView model = new ModelAndView();

		User user = userService.getUserByEmail(((User) httpSession.getAttribute("user"))
				.getEmail());
		if (!(user.getRole().equals("admin"))) {
			model.setViewName("error/403");
			return model;
		}

		List<Message> messages = messageService.listMessage();

		model.addObject("messages", messages.size());

		List<User> users = userService.listUser();

		int usersPayant = 0;
		float gain = 0.0f;
		int achats = 0;
		for (User u : users)
			if (u.getMesScenarii().size() > 0) {
				usersPayant++;
				achats += u.getMesScenarii().size();
				for (UserHasScenario s : u.getMesScenarii())
					gain += s.getPrixAchat();
			}
		model.addObject("users", users.size());
		model.addObject("usersPayant", usersPayant);
		model.addObject("usersGratuit", users.size() - usersPayant);

		model.addObject("achats", achats);
		model.addObject("gain", gain);
		model.setViewName("admin/dashboard");
		return model;
	}
}
