package Service;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

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
}
