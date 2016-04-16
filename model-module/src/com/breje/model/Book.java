package com.breje.model;

import java.io.Serializable;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;

/**
 * 
 * @author Raul Breje
 * 
 */
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8555840175159679233L;
	private String author = null;
	private String title = null;
	private int id = -1;
	private int available = -1;

	public Book(int id, String author, String title, int available) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, Book.class);
		this.id = id;
		this.author = author;
		this.title = title;
		this.available = available;
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, Book.class);
	}

	public String getAuthor() {
		LibraryLogger.logMessage("getAuthor() ENTER", LibraryLoggerType.DEBUG, Book.class);
		LibraryLogger.logMessage("getAuthor() LEAVE", LibraryLoggerType.DEBUG, Book.class);
		return author;
	}

	public void setAuthor(String author) {
		LibraryLogger.logMessage("setAuthor() ENTER", LibraryLoggerType.DEBUG, Book.class);
		this.author = author;
		LibraryLogger.logMessage("setAuthor() LEAVE", LibraryLoggerType.DEBUG, Book.class);
	}

	public String getTitle() {
		LibraryLogger.logMessage("getTitle() ENTER", LibraryLoggerType.DEBUG, Book.class);
		LibraryLogger.logMessage("getTitle() LEAVE", LibraryLoggerType.DEBUG, Book.class);
		return title;
	}

	public void setTitle(String title) {
		LibraryLogger.logMessage("setTitle() ENTER", LibraryLoggerType.DEBUG, Book.class);
		this.title = title;
		LibraryLogger.logMessage("setTitle() LEAVE", LibraryLoggerType.DEBUG, Book.class);
	}

	public int getId() {
		LibraryLogger.logMessage("getId() ENTER", LibraryLoggerType.DEBUG, Book.class);
		LibraryLogger.logMessage("getId() LEAVE", LibraryLoggerType.DEBUG, Book.class);
		return id;
	}

	public void setId(int id) {
		LibraryLogger.logMessage("setId() ENTER", LibraryLoggerType.DEBUG, Book.class);
		this.id = id;
		LibraryLogger.logMessage("setId() LEAVE", LibraryLoggerType.DEBUG, Book.class);
	}

	public int getAvailable() {
		LibraryLogger.logMessage("getAvailable() ENTER", LibraryLoggerType.DEBUG, Book.class);
		LibraryLogger.logMessage("getAvailable() LEAVE", LibraryLoggerType.DEBUG, Book.class);
		return available;
	}

	public void setAvailable(int available) {
		LibraryLogger.logMessage("setAvailable() ENTER", LibraryLoggerType.DEBUG, Book.class);
		this.available = available;
		LibraryLogger.logMessage("setAvailable() LEAVE", LibraryLoggerType.DEBUG, Book.class);
	}

	@Override
	public String toString() {
		LibraryLogger.logMessage("toString() ENTER", LibraryLoggerType.DEBUG, Book.class);
		LibraryLogger.logMessage("toString() LEAVE", LibraryLoggerType.DEBUG, Book.class);
		return "Book [author=" + author + ", title=" + title + ", id=" + id + ", available=" + available + "]";
	}

}
