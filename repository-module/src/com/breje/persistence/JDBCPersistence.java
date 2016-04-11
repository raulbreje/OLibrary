package com.breje.persistence;

import com.breje.repository.book.BookRepository;
import com.breje.repository.book.jdbc.BookRepositoryJDBC;
import com.breje.repository.user.UserRepository;
import com.breje.repository.user.jdbc.UserRepositoryJDBC;

/**
 * 
 * @author Raul Breje
 *
 */
public class JDBCPersistence extends Persistence {

	@Override
	public UserRepository createUserRepository() {
		System.out.println("JDBC user repository created ");
		return new UserRepositoryJDBC();
	}

	@Override
	public BookRepository createBookRepository() {
		System.out.println("JDBC book repository created ");
		return new BookRepositoryJDBC();
	}

}
