package com.dragon.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dragon.access.AccessHelper;
import com.dragon.common.Discuss;
import com.dragon.dao.DiscussDao;

public class DiscussService {

	public static final DiscussService INSTANCE = new DiscussService(); // 单例模式

	private AccessHelper accessHelper = AccessHelper.INSTANCE;

	public DiscussService() {

	}

	public int createDiscuss(Discuss discuss,int studentId) {
		int result = 0;
		Connection connection = accessHelper.createConnection(false);
		DiscussDao discussDao = new DiscussDao(connection);
		try {
			if (!discussDao.retrieveStudentIdByBooknumber(discuss.getBooknumber(),studentId)) {
				result = discussDao.createDiscuss(discuss);
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

	public List<Discuss> retrieveDiscussAll() {
		List<Discuss> discussList = null;
		Connection connection = accessHelper.createConnection(false);
		DiscussDao discussDao = new DiscussDao(connection);
		try {
			discussList = discussDao.retrieveDiscussAll();
		} catch (SQLException e) {
			// 若抛出异常，需回滚事务，最好如下显式地回滚。
			rollback(connection);
			// 将SQLException转化为RuntimeException，如此，该方法调用者就不需显式地处理该SQLException。
			throw new RuntimeException(e);
		} finally {
			// 关闭连接，以释放占用的资源。
			close(connection);
		}
		return discussList;
	}
	public List<Discuss> retrieveDiscussByBooknumber(int booknumber) {
		List<Discuss> discussList = null;
		Connection connection = accessHelper.createConnection(false);
		DiscussDao discussDao = new DiscussDao(connection);
		try {
			discussList = discussDao.retrieveDiscussByBooknumber(booknumber);
		} catch (SQLException e) {
			// 若抛出异常，需回滚事务，最好如下显式地回滚。
			rollback(connection);
			// 将SQLException转化为RuntimeException，如此，该方法调用者就不需显式地处理该SQLException。
			throw new RuntimeException(e);
		} finally {
			// 关闭连接，以释放占用的资源。
			close(connection);
		}
		return discussList;
	}

	public boolean retrieveStudentIdByBooknumber(int booknumber,int studentId) {
		boolean result = false; 
		Connection connection = accessHelper.createConnection(false);
		DiscussDao discussDao = new DiscussDao(connection);
		
		try {
			
			result = discussDao.retrieveStudentIdByBooknumber(booknumber,studentId);
			
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
