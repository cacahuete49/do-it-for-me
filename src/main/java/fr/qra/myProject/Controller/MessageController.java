package fr.qra.myProject.Controller;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.qra.myProject.Model.Message;
import fr.qra.myProject.Model.User;
import fr.qra.myProject.Service.MessageService;
import fr.qra.myProject.Service.UserService;

/**
 * 
 * @author quentin
 *
 */
@Controller
@SessionAttributes("user")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	/***		PUBLIC		***/
	
	@RequestMapping( method = RequestMethod.GET,value = "/contact" )
	public ModelAndView boutique(Locale locale, Model model, HttpSession session ,HttpServletRequest request) {
		Message m = new Message();
		m.setIp(request.getRemoteAddr());
		User user = (User)session.getAttribute("user");
		if (user==null) {
			user = new User();
		}
		model.addAttribute("user", user);
		model.addAttribute("message", m);
		return new ModelAndView("contact");
	}
	
	@RequestMapping( method = RequestMethod.POST , value = {"addMessage"})
	public String addMessage( @ModelAttribute("message") @Valid Message message, BindingResult result , Model model ,HttpServletRequest request) {
		if (result.hasErrors()){
			return "contact";
		}
		message.setDateEnvoi(new Date());
		message.setIp(request.getRemoteAddr());
		messageService.addMessage(message);
		
		if (model.containsAttribute("user"))
			System.out.println("utilisateur déjà présent");
		else
			model.addAttribute("user", new User());
		return "merci";
	}
	
	
	/***		FIN PUBLIC		***/
}
