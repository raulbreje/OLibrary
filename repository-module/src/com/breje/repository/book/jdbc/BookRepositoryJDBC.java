package com.breje.repository.book.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.model.Book;
import com.breje.model.impl.BookImpl;
import com.breje.persistence.utils.JDBCUtils;
import com.breje.persistence.utils.SQLHelper;
import com.breje.repository.book.BookRepository;

/**
 * 
 * @author Raul Breje
 *
 */
public class BookRepositoryJDBC implements BookRepository {

	@Override
	public List<Book> getAvailableBooks() {
		LibraryLogger.logMessage("getAvailableBooks() ENTER", LibraryLoggerType.DEBUG, BookRepositoryJDBC.class);
		Connection connection = JDBCUtils.getConnection();
		List<Book> availableBooks = new ArrayList<>();
		try {
			String sql = SQLHelper.SELECT_AVAILABLE_BOOKS;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				availableBooks.add(
						new BookImpl(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4)));
			}
		} catch (SQLException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, BookRepositoryJDBC.class);
		}
		LibraryLogger.logMessage("getAvailableBooks() LEAVE", LibraryLoggerType.DEBUG, BookRepositoryJDBC.class);
		return availableBooks;
	}

	@Override
	public List<Book> getUserBooks(int userId) {
		LibraryLogger.logMessage("getUserBooks() ENTER", LibraryLoggerType.DEBUG, BookRepositoryJDBC.class);
		Connection connection = JDBCUtils.getConnection();
		List<Book> usersBooks = new ArrayList<>();
		try {
			String sql = SQLHelper.SELECT_USER_BOOKS_SQL.format(new Object[] { userId });
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				usersBooks.add(
						new BookImpl(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4)));
			}
		} catch (SQLException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, BookRepositoryJDBC.class);
		}
		LibraryLogger.logMessage("getUserBooks() LEAVE", LibraryLoggerType.DEBUG, BookRepositoryJDBC.class);
		return usersBooks;
	}

	@Override
	public List<Book> searchBooks(String key) {
		LibraryLogger.logMessage("searchBooks() ENTER", LibraryLoggerType.DEBUG, BookRepositoryJDBC.class);
		Connection connection = JDBCUtils.getConnection();
		List<Book> foundBooks = new ArrayList<>();
		try {
			String sql = SQLHelper.SEARCH_BOOKS_BY_SQL
					.format(new Object[] { SQLHelper.BOOK_TITLE, SQLHelper.toQuotedString("%" + key + "%") });
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				foundBooks.add(
						new BookImpl(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4)));
			}
		} catch (SQLException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, BookRepositoryJDBC.class);
		}
		LibraryLogger.logMessage("searchBooks() LEAVE", LibraryLoggerType.DEBUG, BookRepositoryJDBC.class);
		return foundBooks;
	}

	@Override
	public int borrowBook(int userId, int bookId) {
		LibraryLogger.logMessage("borrowBook() ENTER", LibraryLoggerType.DEBUG, BookRepositoryJDBC.class);
		Connection connection = JDBCUtils.getConnection();
		int quantity = 0;
		try {
			String changeQuantitySql = SQLHelper.SET_AVAILABILITY_BOOK_SQL.format(new Object[] { "-1", bookId });
			PreparedStatement changeQuantityStatement = connection.prepareStatement(changeQuantitySql);
			changeQuantityStatement.executeUpdate();
			String borrowSql = SQLHelper.INSERT_BORROW_SQL.format(new Object[] { userId, bookId });
			PreparedStatement borrowStatement = connection.prepareStatement(borrowSql);
			borrowStatement.executeUpdate();
			String availabilitySql = SQLHelper.GET_AVAILABILITY_OF_BOOK_SQL.format(new Object[] { bookId });
			PreparedStatement availableQuantityStatement = connection.prepareStatement(availabilitySql);
			ResultSet resultSet = availableQuantityStatement.executeQuery();
			if (resultSet.next()) {
				quantity = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, BookRepositoryJDBC.class);
		}
		LibraryLogger.logMessage("borrowBook() LEAVE", LibraryLoggerType.DEBUG, BookRepositoryJDBC.class);
		return quantity;
	}

	@Override
	public Book returnBook(int userId, int bookId) {
		LibraryLogger.logMessage("returnBook() ENTER", LibraryLoggerType.DEBUG, BookRepositoryJDBC.class);
		Connection connection = JDBCUtils.getConnection();
		Book returned = null;
		try {
			String changeQuantitySql = SQLHelper.SET_AVAILABILITY_BOOK_SQL.format(new Object[] { "+1", bookId });
			PreparedStatement changeQuantityStatement = connection.prepareStatement(changeQuantitySql);
			changeQuantityStatement.executeUpdate();
			String returnBookSql = SQLHelper.REMOVE_BORROW_SQL.format(new Object[] { userId, bookId });
			PreparedStatement returnStatement = connection.prepareStatement(returnBookSql);
			returnStatement.executeUpdate();
			String returnedBookSql = SQLHelper.SELECT_AVAILABLE_BOOKS;
			// String returnedBookSql = SQLHelper.GET_BOOK_SQL.format(new
			// Object[] { bookId });
			PreparedStatement selectReturnedBook = connection.prepareStatement(returnedBookSql);
			ResultSet resultSet = selectReturnedBook.executeQuery();
			if (resultSet.next()) {
				returned = new BookImpl(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4));
			}
		} catch (SQLException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, BookRepositoryJDBC.class);
		}
		LibraryLogger.logMessage("returnBook() LEAVE", LibraryLoggerType.DEBUG, BookRepositoryJDBC.class);
		return returned;
	}
}
