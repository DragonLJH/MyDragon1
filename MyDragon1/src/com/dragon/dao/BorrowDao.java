package com.dragon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowDao {
  
  public Connection connection;
  
  public BorrowDao(Connection connection){
    this.connection = connection;
  }
  
  public int createBorrow(int studentId ,int booknumber) throws SQLException { 
    int result = 0;
    
    String sql = "insert into borrowrecord (student_id ,booknumber) values (?,?)";
    
    PreparedStatement statement = null;
    
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, studentId);
      statement.setInt(2, booknumber);
      
      result =  statement.executeUpdate();
      
    }finally{
   // 关闭statement，以释放占用的资源。
      if (statement != null) {
        statement.close();
      }
    }
    return result;
  }
  
  public List<Integer> retrieveBooknumberByStudentId(int studentId) throws SQLException {
    List<Integer> recordList = new ArrayList<Integer>();
    
    String sql = "select booknumber from borrowrecord where student_id = ?";
    
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement(sql);
      
      statement.setInt(1,studentId);
      
      ResultSet resultSet = statement.executeQuery();
      
      while(resultSet.next()) {
        recordList.add(resultSet.getInt(1));
      }
      
    }finally {
      // 关闭statement，以释放占用的资源。
      if (statement != null) {
        statement.close();
      }
    }
    
    return recordList;
  }
  
}
