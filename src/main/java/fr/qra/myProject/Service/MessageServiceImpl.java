/**
 * 
 */
package fr.qra.myProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.qra.myProject.DAO.MessageDAO;
import fr.qra.myProject.Model.Message;

/**
 * @author quentin
 *
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDAO messageDAO;
	
	@Override
	public void addMessage(Message message) {
		messageDAO.addMessage(message);
	}

	@Override
	public void removeMessage(long id) {
		messageDAO.removeMessage(id);
	}

	@Override
	public Message getMessage(long id) {
		return messageDAO.getMessage(id);
	}

	@Override
	public List<Message> listMessage() {
		return messageDAO.listMessage();
	}

}
