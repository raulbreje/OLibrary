package com.breje.persistence;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
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
		LibraryLogger.logMessage("createUserRepository() ENTER", LibraryLoggerType.DEBUG, JDBCPersistence.class);
		LibraryLogger.logMessage("createUserRepository() LEAVE", LibraryLoggerType.DEBUG, JDBCPersistence.class);
		return new UserRepositoryJDBC();
	}

	@Override
	public BookRepository createBookRepository() {
		LibraryLogger.logMessage("createBookRepository() ENTER", LibraryLoggerType.DEBUG, JDBCPersistence.class);
		LibraryLogger.logMessage("createBookRepository() LEAVE", LibraryLoggerType.DEBUG, JDBCPersistence.class);
		return new BookRepositoryJDBC();
	}

}
