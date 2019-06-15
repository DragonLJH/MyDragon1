package com.dragon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dragon.common.Management;

public class UploadDao {

  private Connection connection;

  public UploadDao(Connection connection) {
    this.connection = connection;
  }

  public void createAll(Management management) throws SQLException {
    String sql = "insert into management (name,summary,quantity,author,bookimages,type) values (?,?,?,?,?,?)";

    PreparedStatement statement = null;

    try {
      statement = connection.prepareStatement(sql);
      int i = 1;
      statement.setString(i++, management.getName());
      statement.setString(i++, management.getSummary());
      statement.setInt(i++, management.getQuantity());
      statement.setString(i++, management.getAuthor());
      statement.setString(i++, management.getImages());
      statement.setString(i++, management.getType());
      statement.executeUpdate();

    } finally {
      // 关闭statement，以释放占用的资源。
      if (statement != null) {
        statement.close();
      }
    }

  }

}
