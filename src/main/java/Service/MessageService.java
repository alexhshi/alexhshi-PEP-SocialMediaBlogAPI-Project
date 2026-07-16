package Service;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageService {
  public MessageDAO msgDAO;

  public MessageService() {
    msgDAO = new MessageDAO();
  }

  public Message addMessage(Message msg) {
    //AccountService acctService = new AccountService();
    AccountDAO acctDAO = new AccountDAO();

    if (msg.getMessage_text().length() > 0 && msg.getMessage_text().length() <= 255 && acctDAO.getAccountByID(msg.getPosted_by()) != null) {
      return this.msgDAO.addMessage(msg);
    } else {
      return null;
    }
  }

  public List<Message> getAllMessages() {
    return this.msgDAO.getAllMessages();
  }
  public Message getMessageByID(int id) {
    return this.msgDAO.getMessageByID(id);
  }
  public Message deleteMessageByID(int id) {
    return this.msgDAO.deleteMessageByID(id);
  }
  public Message updateMessageByID(int id, String newMsgTxt) {
    Message msg = this.msgDAO.getMessageByID(id);
    if (msg != null && newMsgTxt.length() > 0 && newMsgTxt.length() <= 255) {
      this.msgDAO.updateMessagesByID(id, newMsgTxt);
      msg.setMessage_text(newMsgTxt);
      return msg;
    } else {
      return null;
    }
  }
  public List<Message> getMessagesByUser(int id) {
    return this.msgDAO.getMessagesByUser(id);
  }
}
