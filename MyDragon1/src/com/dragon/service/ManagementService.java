package com.dragon.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dragon.access.AccessHelper;
import com.dragon.common.Management;
import com.dragon.dao.ManagementDao;

public class ManagementService {

  public static final ManagementService INSTANCE = new ManagementService(); // 创建单例

  private AccessHelper accessHelper = AccessHelper.INSTANCE; // 资源访问助手

  public ManagementService() {

  }

  public List<Management> retrieveManagementByselectName(String optionName,String selectName) {

    // 创建数据库连接，并关闭自动提交（autoCommit）。
    Connection connection = accessHelper.createConnection(false);

    ManagementDao managementDao = new ManagementDao(connection);
    
    List<Management> listManagement = new ArrayList<Management>();

    try {
      listManagement = managementDao.retrieveManagementByselectName(optionName,selectName);
      // 提交事务。
      connection.commit();

    } catch (SQLException e) {
      // 若抛出异常，需回滚事务，最好如下显式地回滚。
      rollback(connection);
      // 将SQLException转化为RuntimeException，如此，该方法调用者就不需显式地处理该SQLException。
      throw new RuntimeException(e);

    } finally {
      // 关闭连接，以释放占用的资源。
      close(connection);

    }

    return listManagement;
  }
  
  
  public List<Management> retrieveManagementAllNameAndImages(){
    
    List<Management> allListName = new ArrayList<Management>();
    
    // 创建数据库连接，并关闭自动提交（autoCommit）。
    Connection connection = accessHelper.createConnection(false);
    
    ManagementDao managementDao = new ManagementDao(connection);
    
    try {
      allListName = managementDao.retrieveManagementAllNameAndImages();
      
      //提交事务
      connection.commit();
    } catch (SQLException e) {
      // 若抛出异常，需回滚事务，最好如下显式地回滚。
      rollback(connection);
      // 将SQLException转化为RuntimeException，如此，该方法调用者就不需显式地处理该SQLException。
      throw new RuntimeException(e);
    }finally {
      // 关闭连接，以释放占用的资源。
      close(connection);
    }
    return allListName;
    
  }
  
  public Management retrieveManagementAllNameAndImagesByBooknumber(int booknumber){
    Management management = new Management();
    
    Connection connection = accessHelper.createConnection(false);
    
    ManagementDao managementDao = new ManagementDao(connection);
    try {
    management = managementDao.retrieveManagementAllNameAndImagesByBooknumber(booknumber);
    
    connection.commit();
    } catch (SQLException e) {
      // 若抛出异常，需回滚事务，最好如下显式地回滚。
      rollback(connection);
      // 将SQLException转化为RuntimeException，如此，该方法调用者就不需显式地处理该SQLException。
      throw new RuntimeException(e);
    }finally {
      // 关闭连接，以释放占用的资源。
      close(connection);
    }
    return management;
  }
  
  

  private void rollback(Connection connection) {
    try {
      connection.rollback();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void close(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
