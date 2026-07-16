package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
  public Message addMessage(Message msg) {
    Connection connection = ConnectionUtil.getConnection();
    try {
      String sql = "insert into message (posted_by, message_text, time_posted_epoch) values (?, ?, ?);";
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

      ps.setInt(1, msg.getPosted_by());
      ps.setString(2, msg.getMessage_text());
      ps.setLong(3, msg.getTime_posted_epoch());

      ps.executeUpdate();
      ResultSet pkeyResultSet = ps.getGeneratedKeys();
      if(pkeyResultSet.next()) {
        int generated_message_id = (int) pkeyResultSet.getLong(1);
        return new Message(generated_message_id, msg.getPosted_by(), msg.getMessage_text(), msg.getTime_posted_epoch());
      }
    }catch(SQLException e){
      System.out.println(e.getMessage());
  }
  return null;
  }
}
