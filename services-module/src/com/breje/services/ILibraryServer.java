package com.breje.services;

import java.util.List;

import com.breje.exceptions.LibraryException;
import com.breje.model.User;
import com.breje.model.impl.BookImpl;

public interface ILibraryServer {
	User login(String userName, String passwd, ILibraryClient client) throws LibraryException;

	void logout(int userId, ILibraryClient client) throws LibraryException;

	List<BookImpl> getAvailableBooks() throws LibraryException;

	List<BookImpl> getUserBooks(int userId) throws LibraryException;

	List<BookImpl> searchBooks(String key) throws LibraryException;

	void borrowBook(int userId, int bookId) throws LibraryException;

	void returnBook(int userId, int bookId) throws LibraryException;

}
