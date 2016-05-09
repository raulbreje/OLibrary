package com.breje.repository.user.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.model.User;
import com.breje.persistence.utils.JDBCUtils;
import com.breje.persistence.utils.SQLHelper;
import com.breje.repository.user.UserRepository;

/**
 * 
 * @author Raul Breje
 *
 */
public class UserRepositoryJDBC implements UserRepository {

	@Override
	public User verifyUser(String userName, String password) {
		LibraryLogger.logMessage("borrowBook() ENTER", LibraryLoggerType.DEBUG, UserRepositoryJDBC.class);
		Connection connection = JDBCUtils.getConnection();
		User user = null;
		try {
			String sql = SQLHelper.LOGIN_SQL
					.format(new Object[] { SQLHelper.toQuotedString(userName), SQLHelper.toQuotedString(password) });
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				user = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
			}
			LibraryLogger.logMessage("Verify user: " + userName, LibraryLoggerType.INFO, UserRepositoryJDBC.class);
			return user;
		} catch (SQLException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, UserRepositoryJDBC.class);
		}
		LibraryLogger.logMessage("borrowBook() LEAVE", LibraryLoggerType.DEBUG, UserRepositoryJDBC.class);
		return user;
	}

}
