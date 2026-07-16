package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

  public Account addAccount(Account acct) {

    Connection connection = ConnectionUtil.getConnection();
    try {
      String sql = "insert into account (username, password) values (?, ?);";
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      ps.setString(1, acct.getUsername());
      ps.setString(2, acct.getPassword());

      ps.executeUpdate();
      ResultSet pkeyResultSet = ps.getGeneratedKeys();
      if(pkeyResultSet.next()) {
        int generated_account_id = (int) pkeyResultSet.getLong(1);
        return new Account(generated_account_id, acct.getUsername(), acct.getPassword());
      }
    }catch(SQLException e){
      System.out.println(e.getMessage());
  }
  return null;

  }

  public Account getAccountByUsername(String username) {
    Connection connection = ConnectionUtil.getConnection();
    try {
        //Write SQL logic here
        String sql = "select account_id, username, password from account where username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //write preparedStatement's setInt method here.
        preparedStatement.setString(1, username);

        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Account acct = new Account(rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password"));
            return acct;
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
  }

  public Account getAccountByID(int id) {
    Connection connection = ConnectionUtil.getConnection();
    try {
        //Write SQL logic here
        String sql = "select account_id, username, password from account where account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //write preparedStatement's setInt method here.
        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Account acct = new Account(rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password"));
            return acct;
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
  }
}