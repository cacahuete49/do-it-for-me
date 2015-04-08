package fr.qra.myProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.qra.myProject.DAO.UserDAO;
import fr.qra.myProject.Model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	@Override
	public User updateUser(User user) {
		return userDAO.updateUser(user);
	}

	@Override
	public List<User> listUser() {
		return userDAO.listUser();
	}

	@Override
	public void removeUser(long id) {
		userDAO.removeUser(id);
	}

	@Override
	public User getUser(long id) {
		return userDAO.getUser(id);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}
}
