package com.breje.repository.book;

import java.util.List;

import com.breje.model.Book;
import com.breje.model.impl.BookImpl;

/**
 * 
 * @author Raul Breje
 *
 */
public interface BookRepository {
	List<Book> getAvailableBooks();

	List<Book> getUserBooks(int userId);

	List<Book> searchBooks(String key);

	int borrowBook(int userId, int bookId);

	Book returnBook(int userId, int bookId);
}
