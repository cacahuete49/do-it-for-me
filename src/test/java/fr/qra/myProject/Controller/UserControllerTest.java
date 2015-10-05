package fr.qra.myProject.Controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.hibernate.NonUniqueObjectException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ModelAndView;

import fr.qra.myProject.Model.Scenario;
import fr.qra.myProject.Model.User;
import fr.qra.myProject.Service.ScenarioService;
import fr.qra.myProject.Service.UserService;

@RunWith(EasyMockRunner.class)
public class UserControllerTest extends EasyMockSupport {

	@Mock
	UserService userServiceMock;
	
	@Mock
	ScenarioService scenarioServiceMock;
	
	@TestSubject
	UserController userControllerUnderTest = new UserController();
	
	@Test
	public void testCreateUserOK() {
		//setup
		User user = new User();
		user.setEmail("email@email.com");
		user.setPassword("azertyuiop");
		user.setConfirmPassword("azertyuiop");
		userServiceMock.addUser(EasyMock.anyObject(User.class));
		EasyMock.expect(userServiceMock.getUser(EasyMock.anyString())).andReturn(user);
		
		EasyMock.replay(userServiceMock);
		
		//when
		ModelAndView resultat = userControllerUnderTest.createUser(user);
		
		//verify
		EasyMock.verify(userServiceMock);
		
		assertEquals(resultat.getViewName(),"privee/coordonnees");		
	}
	
	@Test
	public void testCreateUserKO() {
		//setup
		User user = new User();
		user.setEmail("email@email.com");
		user.setPassword("azertyuiop");
		user.setConfirmPassword("poiuytreza");
		
		//when
		ModelAndView resultat = userControllerUnderTest.createUser(user);
		
		//verify
		assertEquals(resultat.getViewName(),"inscription");
		assertEquals(resultat.getModelMap().get("error_signin"),UserController.INCORRECT_PASSWORD);
	}
	
	@Test
	public void testConnectUserOK() {
		//setup
		String mdp = "mdp";
		User user = new User();
		user.setPassword(mdp);
		
		User userBDD = new User();
		String mdpHash = new BCryptPasswordEncoder().encode(mdp);
		userBDD.setPassword(mdpHash);
		
		EasyMock.expect(userServiceMock.getUser(EasyMock.anyString())).andReturn(userBDD);
		
		EasyMock.replay(userServiceMock);
		//when
		ModelAndView resultat = userControllerUnderTest.connectUser(user);
		
		//verify
		EasyMock.verify(userServiceMock);
		assertEquals(resultat.getViewName(),"privee/coordonnees");
		assertEquals(resultat.getModelMap().get("user"),userBDD);
	}
	
	@Test
	public void testConnectUserNoPresent() {
		//setup
		User user = new User();
		user.setEmail("email");
		
		EasyMock.expect(userServiceMock.getUser(EasyMock.anyString())).andThrow(new NullPointerException());
		
		EasyMock.replay(userServiceMock);
		//when
		ModelAndView resultat = userControllerUnderTest.connectUser(user);
		
		//verify
		EasyMock.verify(userServiceMock);
		assertEquals(resultat.getViewName(),"home");
		assertEquals(resultat.getModelMap().get("error"),UserController.INCORRECT_EMAIL);
	}
	
	@Test
	public void testConnectUserBadPassword() {
		//setup
		User user = new User();
		user.setPassword("mdp");
		
		User userBDD = new User();
		userBDD.setPassword("fail");
		
		EasyMock.expect(userServiceMock.getUser(EasyMock.anyString())).andReturn(userBDD);
		
		EasyMock.replay(userServiceMock);
		//when
		ModelAndView resultat = userControllerUnderTest.connectUser(user);
		
		//verify
		EasyMock.verify(userServiceMock);
		assertEquals(resultat.getViewName(),"home");
		assertEquals(resultat.getModelMap().get("error"),UserController.INCORRECT_PASSWORD);
	}

	@Test
	public void testEditUserOK() {
		//setup
		
		User user = new User(1,"nom","prenom","test@test.com","user",null);
		user.setPassword("testtest");
		user.setConfirmPassword("testtest");
		
		User userBDD = new User();
		EasyMock.expect(userServiceMock.getUserById(EasyMock.anyLong())).andReturn(userBDD);
		EasyMock.expect(userServiceMock.updateUser(user)).andReturn(null);
		
		EasyMock.replay(userServiceMock);
		
		//when
		ModelAndView resultat = userControllerUnderTest.editUser(user);
		
		//verify
		EasyMock.verify(userServiceMock);
		assertEquals(resultat.getViewName(),"privee/coordonnees");
		
	}

	@Test
	public void testEditUserBadPassword() {
		User user = new User(1,"nom","prenom","test@test.com","user",null);
		user.setPassword("testtest");
		user.setConfirmPassword("failfail");
		
		EasyMock.expect(userServiceMock.getUserById(EasyMock.anyLong())).andReturn(user);
		
		EasyMock.replay(userServiceMock);
		
		ModelAndView resultat = userControllerUnderTest.editUser(user);
		
		EasyMock.verify(userServiceMock);
		
		assertEquals(resultat.getViewName(),"privee/editUser");
		assertEquals(resultat.getModelMap().get("error"),UserController.INCORRECT_PASSWORD);
	}
	
	@Test
	public void testAchat() {
		//setup
		Scenario scenario = new Scenario();
		scenario.setId(0);
		scenario.setPrix(0.0f);
		scenario.setNbTelechargement(0);
		
		User user = new User();
		user.setEmail("email");
		user.setId(1);
		
		
		User userBDD = new User();
		userBDD.setPassword("fail");
		
		EasyMock.expect(userServiceMock.getUser(EasyMock.anyString())).andReturn(userBDD);
		EasyMock.expect(userServiceMock.updateUser(EasyMock.anyObject(User.class))).andReturn(userBDD);
		EasyMock.expect(scenarioServiceMock.listScenario()).andReturn(new ArrayList<Scenario>());
		EasyMock.expect(scenarioServiceMock.updateScenario(EasyMock.anyObject(Scenario.class))).andReturn(null);
		
		EasyMock.replay(userServiceMock,scenarioServiceMock);
		//when
		ModelAndView resultat = userControllerUnderTest.achat(scenario, user);
		
		//verify
		EasyMock.verify(userServiceMock,scenarioServiceMock);
		assertEquals(resultat.getViewName(),"redirect:mesScenarii");
		assertEquals(resultat.getModelMap().get("user"),userBDD);
	}
	
	@Test
	public void testAchatDejaPresent() {
		//setup
		Scenario scenario = new Scenario();
		scenario.setId(0);
		scenario.setPrix(0.0f);
		scenario.setNbTelechargement(0);
		
		User user = new User();
		user.setEmail("email");
		user.setId(1);
		
		
		User userBDD = new User();
		userBDD.setPassword("fail");
		
		EasyMock.expect(scenarioServiceMock.listScenario()).andReturn(new ArrayList<Scenario>());
		EasyMock.expect(userServiceMock.getUser(EasyMock.anyString())).andReturn(userBDD);
		EasyMock.expect(userServiceMock.updateUser(EasyMock.anyObject(User.class))).andThrow(new NonUniqueObjectException(userBDD, null));
		
		EasyMock.replay(userServiceMock,scenarioServiceMock);
		//when
		ModelAndView resultat = userControllerUnderTest.achat(scenario, user);
		
		//verify
		EasyMock.verify(userServiceMock,scenarioServiceMock);
		assertEquals(resultat.getViewName(),"redirect:/boutique");
	}

}
