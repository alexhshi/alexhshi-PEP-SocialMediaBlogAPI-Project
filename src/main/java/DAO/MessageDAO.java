package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

  public List<Message> getAllMessages() {
    List<Message> msgs = new ArrayList<>();

    try{
      Connection connection = ConnectionUtil.getConnection();

      String sql = "select message_id, posted_by, message_text, time_posted_epoch from message";
      PreparedStatement ps = connection.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while(rs.next()) {
        Message msg = new Message(rs.getInt("message_id"), rs.getInt("posted_by"), rs.getString("message_text"), rs.getLong("time_posted_epoch"));
        msgs.add(msg);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return msgs;
  }

}
