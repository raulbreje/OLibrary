package com.breje.repository.user;

import com.breje.model.User;

public interface UserRepository {
	User verifyUser(String userName, String password);
}
