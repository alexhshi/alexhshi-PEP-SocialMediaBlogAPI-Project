package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {

  public AccountDAO acctDAO;

  public AccountService() {
    acctDAO = new AccountDAO();
  }

  public Account addAccount(Account acct) {
    if (acct.getUsername().length() > 0 && acct.getPassword().length() >= 4 && this.acctDAO.getAccountByUsername(acct.getUsername()) == null) {
      return this.acctDAO.addAccount(acct);
    } else {
      return null;
    }
  }

  public Account getAccountByLogin(Account acct) {
    Account retrievedAcct = this.acctDAO.getAccountByUsername(acct.getUsername());
    if (retrievedAcct != null && retrievedAcct.getPassword().equals(acct.getPassword())) {
      return retrievedAcct;
    } else {
      return null;
    }
  }
}
