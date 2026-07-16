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
    Message origMsg = this.msgDAO.getMessageByID(id);
    if (origMsg != null && origMsg.getMessage_text().length() > 0 && origMsg.getMessage_text().length() <= 255) {
      return this.msgDAO.updateMessagesByID(id, newMsgTxt);
    }
  }
}
