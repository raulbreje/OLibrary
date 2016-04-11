package com.breje.repository.user.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.breje.model.User;
import com.breje.persistence.JDBCUtils;
import com.breje.repository.user.UserRepository;

/**
 * 
 * @author Raul Breje
 *
 */
public class UserRepositoryJDBC implements UserRepository {

	@Override
	public User verifyUser(String userName, String password) {
		System.out.println("Jdbc verify user");
		Connection connection = JDBCUtils.getConnection();
		User user = null;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from users where user_name=? and password=?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				user = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
			}
			System.out.println("Verify user " + userName);
			return user;
		} catch (SQLException e) {
			System.out.println("Error DB " + e);
		}
		return user;
	}

}
