package com.breje.persistence;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.repository.book.BookRepository;
import com.breje.repository.user.UserRepository;

/**
 * 
 * @author Raul Breje
 *
 */
public abstract class Persistence {

	private static Persistence instance = null;

	protected Persistence() {
	}

	public static Persistence getInstance() {
		LibraryLogger.logMessage("getInstance() ENTER", LibraryLoggerType.DEBUG, Persistence.class);
		if (instance == null) {
			String repositoryName = System.getProperty("persistence-class-name");
			try {
				Class<?> repoClass = Class.forName(repositoryName);
				if (repoClass.getSuperclass().equals(Persistence.class))
					instance = (Persistence) repoClass.newInstance();
			} catch (ClassNotFoundException e) {
				LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, Persistence.class);
			} catch (IllegalAccessException e) {
				LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, Persistence.class);
			} catch (InstantiationException e) {
				LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, Persistence.class);
			}
		}
		LibraryLogger.logMessage("getInstance() LEAVE", LibraryLoggerType.DEBUG, Persistence.class);
		return instance;
	}

	public abstract UserRepository createUserRepository();

	public abstract BookRepository createBookRepository();

}
