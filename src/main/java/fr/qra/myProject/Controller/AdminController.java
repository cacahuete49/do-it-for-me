package fr.qra.myProject.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import fr.qra.myProject.Model.Message;
import fr.qra.myProject.Model.User;
import fr.qra.myProject.Model.UserHasScenario;
import fr.qra.myProject.Service.MessageService;
import fr.qra.myProject.Service.UserService;

@Controller
public class AdminController {
	
	@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public class TeapotException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8345915819331207085L;
	}

	@Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;

    @RequestMapping(method={RequestMethod.GET}, value={"/adminmessages"})
    public ModelAndView allMessage(HttpSession httpSession) {
        ModelAndView model = new ModelAndView();
        User user = (User)httpSession.getAttribute("user");
        if (!(user != null && user.getRole().equals("admin"))) {
            model.setViewName("error/403");
            return model;
        }
        List messages = this.messageService.listMessage();
        model.addObject("messages", (Object)messages);
        model.setViewName("admin/message");
        return model;
    }

    @RequestMapping(method={RequestMethod.GET}, value={"/admin"})
    public ModelAndView dashBoard(HttpSession httpSession) {
        ModelAndView model = new ModelAndView();
        User user = (User)httpSession.getAttribute("user");
        if (!(user != null && user.getRole().equals("admin"))) {
            model.setViewName("error/403");
            return model;
        }
        List<Message> messages = this.messageService.listMessage();
        model.addObject("messages", (Object)messages.size());
        List<User> users = this.userService.listUser();
        int usersPayant = 0;
        float gain = 0.0f;
        int achats = 0;
        for (User u : users) {
            if (u.getMesScenarii().size() <= 0) continue;
            ++usersPayant;
            achats+=u.getMesScenarii().size();
            for (UserHasScenario s : u.getMesScenarii()) {
                gain+=s.getPrixAchat();
            }
        }
        model.addObject("users", (Object)users.size());
        model.addObject("usersPayant", (Object)usersPayant);
        model.addObject("usersGratuit", (Object)(users.size() - usersPayant));
        model.addObject("achats", (Object)achats);
        model.addObject("gain", (Object)Float.valueOf(gain));
        model.setViewName("admin/dashboard");
        return model;
    }

    @RequestMapping(value={"/0xCAFEBABE"})
    public void teapot() {
        throw new TeapotException();
    }
}