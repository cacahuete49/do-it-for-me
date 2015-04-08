package fr.qra.myProject.Service;

import java.util.List;

import fr.qra.myProject.Model.Message;


public interface MessageService {

	public void addMessage(Message Message);

	public void removeMessage(long id);

	public Message getMessage(long id);

	public List<Message> listMessage();
}
