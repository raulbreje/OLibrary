package com.breje.persistence;

import com.breje.repository.book.BookRepository;
import com.breje.repository.book.mock.BookRepositoryMock;
import com.breje.repository.user.UserRepository;
import com.breje.repository.user.mock.UserRepositoryMock;

/**
 * 
 * @author Raul Breje
 *
 */
public class MockPersistence extends Persistence {
	public UserRepository createUserRepository() {
		System.out.println("Mock user repository created ");
		return new UserRepositoryMock();
	}

	@Override
	public BookRepository createBookRepository() {
		System.out.println("Mock book repository created ");
		return new BookRepositoryMock();
	}
}
