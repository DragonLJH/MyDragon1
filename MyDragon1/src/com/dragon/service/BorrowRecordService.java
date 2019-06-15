package com.dragon.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dragon.access.AccessHelper;
import com.dragon.dao.BorrowDao;
import com.dragon.dao.ManagementDao;

public class BorrowRecordService {
  
  public static final BorrowRecordService INSTANCE = new BorrowRecordService(); // 创建单例
  
  private AccessHelper accessHelper = AccessHelper.INSTANCE; // 资源访问助手
  
  public BorrowRecordService() {
    
  }
  
  public int createBorrow(int studentId ,int booknumber) {
    int result = 0;
    
    // 创建数据库连接，并关闭自动提交（autoCommit）。
    Connection connection = accessHelper.createConnection(false);

    BorrowDao borrowDao = new BorrowDao(connection);
    ManagementDao managementDao = new ManagementDao(connection);
    try {
      int update = managementDao.updateManagementByBooknumber(booknumber);
      if(update == 1) {
        result = borrowDao.createBorrow(studentId, booknumber);
      }
      
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
    return result;
  }
  
  public List<Integer> retrieveBooknumberByStudentId(int studentId) {
    List<Integer> recordList= new ArrayList<Integer>();
    
    Connection connection = accessHelper.createConnection(false);
    
    BorrowDao borrowDao = new BorrowDao(connection);
    
    try {
      recordList = borrowDao.retrieveBooknumberByStudentId(studentId);
      
    } catch (SQLException e) {
      // 若抛出异常，需回滚事务，最好如下显式地回滚。
      rollback(connection);
      // 将SQLException转化为RuntimeException，如此，该方法调用者就不需显式地处理该SQLException。
      throw new RuntimeException(e);
    } finally {
      // 关闭连接，以释放占用的资源。
      close(connection);
    }
    return recordList;
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
