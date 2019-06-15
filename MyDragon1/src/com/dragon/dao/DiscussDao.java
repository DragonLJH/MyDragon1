package com.dragon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dragon.common.Discuss;

public class DiscussDao {

  public Connection connection;
  
  public DiscussDao(Connection connection) {
    this.connection = connection;
  }
  
  public int createDiscuss(Discuss discuss) throws SQLException {
    int result = 0;
    String sql = "insert into discuss (student_id,discuss,booknumber) values (?,?,?)";
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, discuss.getStudentId());
      statement.setString(2,discuss.getDiscuss());
      statement.setInt(3,discuss.getBooknumber());
      
      result = statement.executeUpdate();
      
    }finally{
   // 关闭statement，以释放占用的资源。
      if (statement != null) {
        statement.close();
      }
    }
    return result;
  }
  
  public List<Discuss> retrieveDiscussAll() throws SQLException {
    List<Discuss> discussList = null;
    
    String sql = "select student_id,discuss,booknumber from discuss";
    
    Statement statement = null;
    
    try {
      discussList = new ArrayList<Discuss>();
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sql);
      while(resultSet.next()) {
        Discuss discuss = new Discuss();
        discuss.setStudentId(resultSet.getInt(1));
        discuss.setDiscuss(resultSet.getString(2));
        discuss.setBooknumber(resultSet.getInt(3));
        discussList.add(discuss);
      }
    }finally {
      // 关闭statement，以释放占用的资源。
      if (statement != null) {
        statement.close();
      }
    }
    return discussList;
  }
  public List<Discuss> retrieveDiscussByBooknumber(int booknumber) throws SQLException {
	    List<Discuss> discussList = null;
	    
	    String sql = "select student_id,discuss from discuss where booknumber = ?";
	    
	    PreparedStatement statement = null;
	    
	    try {
	      discussList = new ArrayList<Discuss>();
	      statement = connection.prepareStatement(sql);
	      statement.setInt(1, booknumber);
	      
	      ResultSet resultSet = statement.executeQuery();
	      while(resultSet.next()) {
	        Discuss discuss = new Discuss();
	        discuss.setStudentId(resultSet.getInt(1));
	        discuss.setDiscuss(resultSet.getString(2));
	        discussList.add(discuss);
	      }
	    }finally {
	      // 关闭statement，以释放占用的资源。
	      if (statement != null) {
	        statement.close();
	      }
	    }
	    return discussList;
	  }
  
  public boolean retrieveStudentIdByBooknumber(int booknumber,int studentId) throws SQLException {
    boolean result = false;
    String sql = "select student_id from discuss where booknumber = ?";
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, booknumber);
      ResultSet rs = statement.executeQuery();
      while(rs.next()) {
    	  String ts = rs.getString(1);
         if(ts.equals(studentId) ) {
        	 result = true;
         }
        
      }
    }finally {
      // 关闭statement，以释放占用的资源。
      if (statement != null) {
        statement.close();
      }
    }
    return result;
  }
  
}
