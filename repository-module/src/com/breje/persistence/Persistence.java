package com.breje.persistence;

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
		if (instance == null) {
			String repositoryName = System.getProperty("persistence-class-name");
			try {
				Class<?> repoClass = Class.forName(repositoryName);
				if (repoClass.getSuperclass().equals(Persistence.class))
					instance = (Persistence) repoClass.newInstance();
			} catch (ClassNotFoundException e) {
				System.out.println("Persistence exception: " + e);
			} catch (IllegalAccessException e) {
				System.out.println("Persistence excep: " + e);
			} catch (InstantiationException e) {
				System.out.println("Persistence excep: " + e);
			}
		}
		return instance;
	}

	public abstract UserRepository createUserRepository();

	public abstract BookRepository createBookRepository();

}
