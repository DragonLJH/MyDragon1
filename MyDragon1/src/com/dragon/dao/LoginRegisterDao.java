package com.dragon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dragon.common.LoginRegister;

public final class LoginRegisterDao {

	public Connection connection;

	public LoginRegisterDao(Connection connection) {
		this.connection = connection;
	}

	public boolean createLoginRegist(int studentId, String password) throws SQLException {
		int result;
		String sql = "insert into loginregist(student_id,password) values (?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			statement.setString(2, password);
			result = statement.executeUpdate();

		} finally {
			// 关闭statement，以释放占用的资源。
			if (statement != null) {
				statement.close();
			}
		}
		return result != 0;
	}

	public boolean retrieveLoginRegistName(int studentId) throws SQLException {

		boolean result = false;
		String sql = "select student_id from loginregist where student_id = ?";
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result = true;
			}
		} finally {
			// 关闭statement，以释放占用的资源。
			if (statement != null) {
				statement.close();
			}
		}
		return result;
	}

	public LoginRegister retrieveLoginRegistNameAndPassword(int studentId) throws SQLException {

		LoginRegister result = new LoginRegister();
		String sql = "select student_id,password from loginregist where student_id = ?";
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.setStudentId(resultSet.getInt(1));
				result.setPassword(resultSet.getString(2));
			}
		} finally {
			// 关闭statement，以释放占用的资源。
			if (statement != null) {
				statement.close();
			}
		}
		return result;
	}

	public LoginRegister retrieveLoginRegistAllByStudentId(int studentId) throws SQLException {

		LoginRegister result = new LoginRegister();
		String sql = "select student_id,password,student_name,sex,email,home,sign from loginregist where student_id = ?";
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.setStudentId(resultSet.getInt(1));
				result.setPassword(resultSet.getString(2));
				result.setStudentName(resultSet.getString(3));
				result.setSex(resultSet.getString(4));
				result.setEmail(resultSet.getString(5));
				result.setHone(resultSet.getString(6));
				result.setSign(resultSet.getString(7));
			}
		} finally {
			// 关闭statement，以释放占用的资源。
			if (statement != null) {
				statement.close();
			}
		}
		return result;
//		update loginregist set student_name = 'dragon',sex = '男',email = '123456@qq.com',home = '广东',sign = 'is打死U盾有氨甲环酸都看见俺是短上衣 阿达牛啊丢阿姨带收到货啊啊丢啊啊' where student_id = 123
	}

	public int updateLoginRegistByStudentId(LoginRegister loginRegist) throws SQLException {
		int result = 0;
		String sql = "update loginregist set student_name = ?,sex = ?,email = ?,home = ?,sign =? where student_id = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, loginRegist.getStudentName());
			statement.setString(i++, loginRegist.getSex());
			statement.setString(i++, loginRegist.getEmail());
			statement.setString(i++, loginRegist.getHone());
			statement.setString(i++, loginRegist.getSign());
			statement.setInt(i++, loginRegist.getStudentId());
			result = statement.executeUpdate();
		} finally {
			// 关闭statement，以释放占用的资源。
			if (statement != null) {
				statement.close();
			}
		}
		return result;
	}

}
