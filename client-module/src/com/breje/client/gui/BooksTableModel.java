package com.breje.client.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.model.Book;

public class BooksTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -573832890013912350L;
	private List<Book> books;
	private String[] columns;

	public BooksTableModel(boolean showAvailable) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, BooksTableModel.class);
		this.books = new ArrayList<>();
		if (showAvailable) {
			columns = new String[] { "Book ID", "Author", "Name", "Available" };
		} else {
			columns = new String[] { "Book ID", "Author", "Name" };
		}
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, BooksTableModel.class);
	}

	@Override
	public String getColumnName(int column) {
		LibraryLogger.logMessage("getColumnName() ENTER", LibraryLoggerType.DEBUG, BooksTableModel.class);
		LibraryLogger.logMessage("getColumnName() LEAVE", LibraryLoggerType.DEBUG, BooksTableModel.class);
		return columns[column];
	}

	public int getRowCount() {
		LibraryLogger.logMessage("getRowCount() ENTER", LibraryLoggerType.DEBUG, BooksTableModel.class);
		LibraryLogger.logMessage("getRowCount() LEAVE", LibraryLoggerType.DEBUG, BooksTableModel.class);
		return books.size();
	}

	public int getColumnCount() {
		LibraryLogger.logMessage("getColumnCount() ENTER", LibraryLoggerType.DEBUG, BooksTableModel.class);
		LibraryLogger.logMessage("getColumnCount() LEAVE", LibraryLoggerType.DEBUG, BooksTableModel.class);
		return columns.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		LibraryLogger.logMessage("getValueAt() ENTER", LibraryLoggerType.DEBUG, BooksTableModel.class);
		Object obj = null;
		switch (columnIndex) {
		case 0:
			obj = books.get(rowIndex).getBookId();
			break;
		case 1:
			obj = books.get(rowIndex).getAuthor();
			break;
		case 2:
			obj = books.get(rowIndex).getTitle();
			break;
		case 3:
			obj = books.get(rowIndex).getAvailable();
			break;
		}
		LibraryLogger.logMessage("getValueAt() LEAVE", LibraryLoggerType.DEBUG, BooksTableModel.class);
		return obj;
	}

	public void setBooks(List<Book> books) {
		LibraryLogger.logMessage("setBooks() ENTER", LibraryLoggerType.DEBUG, BooksTableModel.class);
		this.books = books;
		fireTableDataChanged();
		LibraryLogger.logMessage("setBooks() LEAVE", LibraryLoggerType.DEBUG, BooksTableModel.class);
	}

	public void addBook(Book book) {
		LibraryLogger.logMessage("addBook() ENTER", LibraryLoggerType.DEBUG, BooksTableModel.class);
		this.books.add(book);
		fireTableDataChanged();
		LibraryLogger.logMessage("addBook() LEAVE", LibraryLoggerType.DEBUG, BooksTableModel.class);
	}

	public Book get(int index) {
		LibraryLogger.logMessage("get() ENTER", LibraryLoggerType.DEBUG, BooksTableModel.class);
		LibraryLogger.logMessage("get() LEAVE", LibraryLoggerType.DEBUG, BooksTableModel.class);
		return books.get(index);
	}

	public Book getBookById(int bookId) {
		LibraryLogger.logMessage("getBookById() ENTER", LibraryLoggerType.DEBUG, BooksTableModel.class);
		for (Book book : books) {
			if (book.getBookId() == bookId) {
				return book;
			}
		}
		return null;

	}

	public void removeBookById(int bookId) {
		Book toRemove = null;
		for (Book book : books) {
			if (book.getBookId() == bookId) {
				toRemove = book;
				break;
			}
		}
		if (toRemove != null) {
			books.remove(toRemove);
		}
		fireTableDataChanged();
	}

}
