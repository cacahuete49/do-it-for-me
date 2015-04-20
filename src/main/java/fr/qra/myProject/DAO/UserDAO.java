package fr.qra.myProject.DAO;

import java.util.List;

import fr.qra.myProject.Model.User;

public interface UserDAO {

	public String addUser(User user);

	public User updateUser(User user);

	public List<User> listUser();

	public void removeUser(String email);

	public User getUser(String email);
}
