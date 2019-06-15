package com.dragon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dragon.common.Management;

public final class ManagementDao {
  public Connection connection;
  
  public ManagementDao(Connection connection){
    this.connection = connection;
  }
  
  public List<Management> retrieveManagementByselectName(String optionName,String selectName) throws SQLException {
    String replaceName = optionName;
    String sql = "select booknumber,name,summary,quantity,author,bookimages from management where "+ replaceName +" = ?";
    List<Management> listManagement = new ArrayList<Management>();
    PreparedStatement statement = null;
    try {
      // 创建statement。
      statement = connection.prepareStatement(sql);
      statement.setString(1, selectName);

      // 执行statement，并取得结果。
      ResultSet resultSet = statement.executeQuery();
      
      while(resultSet.next()) {
        Management management = new Management();
        management.setNumber(resultSet.getInt(1));
        management.setName(resultSet.getString(2));
        management.setSummary(resultSet.getString(3));
        management.setQuantity(resultSet.getInt(4));
        management.setAuthor(resultSet.getString(5));
        management.setImages(resultSet.getString(6)); 
        listManagement.add(management);
      }

    }finally{
   // 关闭statement，以释放占用的资源。
      if (statement != null) {
        statement.close();
      }
    }
    return listManagement;
  }
  
  public List<Management> retrieveManagementAllNameAndImages() throws SQLException {
    List<Management> allListName = new ArrayList<Management>();
    
    String sql = "select name,bookimages,type from management";
    Statement statement = null;
    try {
      statement = connection.createStatement();
      
      ResultSet resultSet = statement.executeQuery(sql);
      
      while(resultSet.next()) {
        Management management = new Management();
        management.setName(resultSet.getString(1));
        management.setImages(resultSet.getString(2));
        management.setType(resultSet.getString(3));
        allListName.add(management);
      }
    }finally{
   // 关闭statement，以释放占用的资源。
      if (statement != null) {
        statement.close();
      }
    }
    return allListName;
  }
  
  
  public int updateManagementByBooknumber(int booknumber) throws SQLException {
    int result = 0;
    String sql = "update management set quantity = quantity - 1 where booknumber = ?";
    
    PreparedStatement statement = null;
    
    try {
      
      statement = connection.prepareStatement(sql);
      statement.setInt(1, booknumber);
      
      result = statement.executeUpdate();
      
    }finally{
   // 关闭statement，以释放占用的资源。
      if (statement != null) {
        statement.close();
      }
    }
    return result;
  }
  
  public Management retrieveManagementAllNameAndImagesByBooknumber(int booknumber) throws SQLException {
    Management management = new Management();
    
    String sql = "select name,bookimages from management where booknumber = ?";
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement(sql);
      statement.setInt(1, booknumber);
      
      ResultSet resultSet = statement.executeQuery();
      
      while(resultSet.next()) {
        management.setName(resultSet.getString(1));
        management.setImages(resultSet.getString(2));
      }
      
    }finally{
   // 关闭statement，以释放占用的资源。
      if (statement != null) {
        statement.close();
      }
    }
    return management;
  }
  
  
}
