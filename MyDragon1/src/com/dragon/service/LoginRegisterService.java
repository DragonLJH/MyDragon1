package com.dragon.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dragon.access.AccessHelper;
import com.dragon.common.LoginRegister;
import com.dragon.dao.LoginRegisterDao;

public class LoginRegisterService {
	public static final LoginRegisterService INSTANCE = new LoginRegisterService(); // 创建单例

	private AccessHelper accessHelper = AccessHelper.INSTANCE; // 资源访问助手

	public LoginRegisterService() {

	}

	public boolean createLoginRegist(int studentId, String password) {

		// 创建数据库连接，并关闭自动提交（autoCommit）。
		Connection connection = accessHelper.createConnection(false);

		LoginRegisterDao loginRegisterDao = new LoginRegisterDao(connection);

		boolean result = false;

		try {
			if (!loginRegisterDao.retrieveLoginRegistName(studentId)) {
				result = loginRegisterDao.createLoginRegist(studentId, password);
			}
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

	public boolean retrieveLoginRegistName(int studentId) {
		// 创建数据库连接，并关闭自动提交（autoCommit）。
		Connection connection = accessHelper.createConnection(false);

		LoginRegisterDao loginRegisterDao = new LoginRegisterDao(connection);

		boolean result = false;

		try {
			result = loginRegisterDao.retrieveLoginRegistName(studentId);
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

	public LoginRegister retrieveLoginRegistNameAndPassword(int studentId) {

		// 创建数据库连接，并关闭自动提交（autoCommit）。
		Connection connection = accessHelper.createConnection(false);

		LoginRegisterDao loginRegisterDao = new LoginRegisterDao(connection);

		LoginRegister result = null;

		try {
			result = loginRegisterDao.retrieveLoginRegistNameAndPassword(studentId);
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

	public LoginRegister retrieveLoginRegistAllByStudentId(int studentId) {
		// 创建数据库连接，并关闭自动提交（autoCommit）。
		Connection connection = accessHelper.createConnection(false);

		LoginRegisterDao loginRegisterDao = new LoginRegisterDao(connection);

		LoginRegister result = null;

		try {
			result = loginRegisterDao.retrieveLoginRegistAllByStudentId(studentId);
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

	public int updateLoginRegistByStudentId(LoginRegister loginRegist) {
		// 创建数据库连接，并关闭自动提交（autoCommit）。
		Connection connection = accessHelper.createConnection(false);

		LoginRegisterDao loginRegisterDao = new LoginRegisterDao(connection);

		int result = 0;

		try {
			result = loginRegisterDao.updateLoginRegistByStudentId(loginRegist);
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
