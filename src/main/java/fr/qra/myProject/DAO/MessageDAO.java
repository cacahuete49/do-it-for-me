package fr.qra.myProject.DAO;

import java.util.List;

import fr.qra.myProject.Model.Message;

public interface MessageDAO {

	public long addMessage(Message message);

	public void removeMessage(long id);

	public Message getMessage(long id);

	public List<Message> listMessage();
}
