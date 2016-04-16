package com.breje.persistence.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;

/**
 * 
 * @author Raul Breje
 *
 */
public class JDBCUtils {

	private static Connection instance = null;

	private static Connection getNewConnection() {
		LibraryLogger.logMessage("getNewConnection() ENTER", LibraryLoggerType.DEBUG, JDBCUtils.class);
		String driver = System.getProperty("driver");
		String url = System.getProperty("url");
		String user = System.getProperty("username");
		String pass = System.getProperty("password");
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			LibraryLogger.logMessage("Error loading driver: " + e, LibraryLoggerType.ERROR, JDBCUtils.class);
		} catch (SQLException e) {
			LibraryLogger.logMessage("Error getting connection: " + e, LibraryLoggerType.ERROR, JDBCUtils.class);
		}
		LibraryLogger.logMessage("getNewConnection() LEAVE", LibraryLoggerType.DEBUG, JDBCUtils.class);
		return con;
	}

	public static Connection getConnection() {
		LibraryLogger.logMessage("getConnection() ENTER", LibraryLoggerType.DEBUG, JDBCUtils.class);
		try {
			if (instance == null || instance.isClosed())
				instance = getNewConnection();
		} catch (SQLException e) {
			LibraryLogger.logMessage("Error database: " + e, LibraryLoggerType.ERROR, JDBCUtils.class);
		}
		LibraryLogger.logMessage("getConnection() LEAVE", LibraryLoggerType.DEBUG, JDBCUtils.class);
		return instance;
	}
}
