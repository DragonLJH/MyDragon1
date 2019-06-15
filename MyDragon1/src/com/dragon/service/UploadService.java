package com.dragon.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dragon.access.AccessHelper;
import com.dragon.common.Management;
import com.dragon.dao.UploadDao;

public final class UploadService {
  public static final UploadService INSTANCE = new UploadService(); // 创建单例

  private AccessHelper accessHelper = AccessHelper.INSTANCE; // 资源访问助手

  public UploadService() {

  }

  public void createAll(Management management)  {
    
    // 创建数据库连接，并关闭自动提交（autoCommit）。
    Connection connection = accessHelper.createConnection(false);

    UploadDao UploadDao = new UploadDao(connection);

    try {
      UploadDao.createAll(management);
      
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
