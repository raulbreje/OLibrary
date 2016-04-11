package com.breje.repository.user.mock;

import java.util.Map;
import java.util.TreeMap;

import com.breje.model.User;
import com.breje.repository.user.UserRepository;

/**
 * 
 * @author Raul Breje
 *
 */
public class UserRepositoryMock implements UserRepository {
	private Map<String, User> allUsers;

	public UserRepositoryMock() {
		allUsers = new TreeMap<>();
		populateUsers();
	}

	public User verifyUser(String userName, String password) {
		User user = allUsers.get(userName);
		if (user != null) {
			if (user.getPasswd().equals(password)) {
				return user;
			}
		}
		return null;
	}

	private void populateUsers() {
		User ana = new User(1, "ana", "ana", "Popescu Ana");
		User mihai = new User(2, "mihai", "mihai", "Ionescu Mihai");
		User ion = new User(3, "ion", "ion", "Vasilescu Ion");
		User maria = new User(4, "maria", "maria", "Marinescu Maria");
		User test = new User(5, "test", "test", "Test user");
		allUsers.put(ana.getUserName(), ana);
		allUsers.put(mihai.getUserName(), mihai);
		allUsers.put(ion.getUserName(), ion);
		allUsers.put(maria.getUserName(), maria);
		allUsers.put(test.getUserName(), test);
	}
}
