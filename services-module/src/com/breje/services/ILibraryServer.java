package com.breje.services;

import java.util.List;

import com.breje.exceptions.LibraryException;
import com.breje.model.Book;
import com.breje.model.User;

public interface ILibraryServer {
	User login(String userName, String passwd, ILibraryClient client) throws LibraryException;

	void logout(int userId, ILibraryClient client) throws LibraryException;

	List<Book> getAvailableBooks() throws LibraryException;

	List<Book> getUserBooks(int userId) throws LibraryException;

	List<Book> searchBooks(String key) throws LibraryException;

	void borrowBook(int userId, int bookId) throws LibraryException;

	void returnBook(int userId, int bookId) throws LibraryException;

}
