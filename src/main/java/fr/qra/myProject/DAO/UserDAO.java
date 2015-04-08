package fr.qra.myProject.DAO;

import java.util.List;

import fr.qra.myProject.Model.User;

public interface UserDAO {

	public long addUser(User user);

	public User updateUser(User user);

	public List<User> listUser();

	public void removeUser(long id);

	public User getUser(long id);

	public User getUserByEmail(String email);

}
