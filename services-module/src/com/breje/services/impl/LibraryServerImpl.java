package com.breje.services.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.breje.exceptions.LibraryException;
import com.breje.model.Book;
import com.breje.model.User;
import com.breje.services.ILibraryClient;
import com.breje.services.ILibraryServer;

public class LibraryServerImpl implements ILibraryServer {
	// private UserRepository userRepository;
	// private BookRepository bookRepository;
	private Map<Integer, ILibraryClient> loggedClients;

	public LibraryServerImpl() {
		// userRepository = Persistence.getInstance().createUserRepository();
		// bookRepository = Persistence.getInstance().createBookRepository();
		loggedClients = new ConcurrentHashMap<>();
	}

	public synchronized User login(String userName, String password, ILibraryClient client) throws LibraryException {
		User user = null;
		// User user = userRepository.verifyUser(userName, password);
		if (user != null) {
			if (loggedClients.get(user.getId()) != null)
				throw new LibraryException("User already logged in.");
			loggedClients.put(user.getId(), client);
		} else
			throw new LibraryException("Authentication failed.");
		return user;
	}

	public synchronized void logout(int userId, ILibraryClient client) throws LibraryException {
		ILibraryClient localClient = loggedClients.remove(userId);
		if (localClient == null)
			throw new LibraryException("User " + userId + " is not logged in.");
	}

	@Override
	public List<Book> getAvailableBooks() throws LibraryException {
		return null;
		// return bookRepository.getAvailableBooks();
	}

	@Override
	public List<Book> getUserBooks(int userId) throws LibraryException {
		return null;
		// return bookRepository.getUserBooks(userId);
	}

	@Override
	public List<Book> searchBooks(String key) throws LibraryException {
		return null;
		// return bookRepository.searchBooks(key);
	}

	@Override
	public void borrowBook(int userId, int bookId) throws LibraryException {
		int newQuantity = 0;
		// int newQuantity = bookRepository.borrowBook(userId, bookId);
		for (ILibraryClient libraryClient : loggedClients.values()) {
			libraryClient.bookUpdated(bookId, newQuantity);
		}
	}

	@Override
	public void returnBook(int userId, int bookId) throws LibraryException {
		Book returned = null;
		// Book returned = bookRepository.returnBook(userId, bookId);
		for (ILibraryClient libraryClient : loggedClients.values()) {
			libraryClient.bookReturned(returned.getId(), returned.getAuthor(), returned.getTitle());
		}
	}

	private boolean isLogged(User u) {
		return loggedClients.get(u.getUserName()) != null;
	}
}
