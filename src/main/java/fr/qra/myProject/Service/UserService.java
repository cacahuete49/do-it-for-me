package fr.qra.myProject.Service;

import java.util.List;

import fr.qra.myProject.Model.User;

public interface UserService {

	public void addUser(User user);

	public List<User> listUser();

	public void removeUser(String email);

	public User getUser(String email);

	public User updateUser(User user);
}
