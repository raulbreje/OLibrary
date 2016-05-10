package com.breje.model.impl;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.model.Book;

/**
 * 
 * @author Raul Breje
 * 
 */
public class BookImpl implements Book {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8555840175159679233L;
	private String author = null;
	private String title = null;
	private int id = -1;
	private int available = -1;

	public BookImpl(int id, String author, String title, int available) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, BookImpl.class);
		this.id = id;
		this.author = author;
		this.title = title;
		this.available = available;
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, BookImpl.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.Book#getAuthor()
	 */
	@Override
	public String getAuthor() {
		LibraryLogger.logMessage("getAuthor() ENTER", LibraryLoggerType.DEBUG, BookImpl.class);
		LibraryLogger.logMessage("getAuthor() LEAVE", LibraryLoggerType.DEBUG, BookImpl.class);
		return author;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.Book#setAuthor(java.lang.String)
	 */
	@Override
	public void setAuthor(String author) {
		LibraryLogger.logMessage("setAuthor() ENTER", LibraryLoggerType.DEBUG, BookImpl.class);
		this.author = author;
		LibraryLogger.logMessage("setAuthor() LEAVE", LibraryLoggerType.DEBUG, BookImpl.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.Book#getTitle()
	 */
	@Override
	public String getTitle() {
		LibraryLogger.logMessage("getTitle() ENTER", LibraryLoggerType.DEBUG, BookImpl.class);
		LibraryLogger.logMessage("getTitle() LEAVE", LibraryLoggerType.DEBUG, BookImpl.class);
		return title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.Book#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		LibraryLogger.logMessage("setTitle() ENTER", LibraryLoggerType.DEBUG, BookImpl.class);
		this.title = title;
		LibraryLogger.logMessage("setTitle() LEAVE", LibraryLoggerType.DEBUG, BookImpl.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.Book#getId()
	 */
	@Override
	public int getId() {
		LibraryLogger.logMessage("getId() ENTER", LibraryLoggerType.DEBUG, BookImpl.class);
		LibraryLogger.logMessage("getId() LEAVE", LibraryLoggerType.DEBUG, BookImpl.class);
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.Book#setId(int)
	 */
	@Override
	public void setId(int id) {
		LibraryLogger.logMessage("setId() ENTER", LibraryLoggerType.DEBUG, BookImpl.class);
		this.id = id;
		LibraryLogger.logMessage("setId() LEAVE", LibraryLoggerType.DEBUG, BookImpl.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.Book#getAvailable()
	 */
	@Override
	public int getAvailable() {
		LibraryLogger.logMessage("getAvailable() ENTER", LibraryLoggerType.DEBUG, BookImpl.class);
		LibraryLogger.logMessage("getAvailable() LEAVE", LibraryLoggerType.DEBUG, BookImpl.class);
		return available;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.Book#setAvailable(int)
	 */
	@Override
	public void setAvailable(int available) {
		LibraryLogger.logMessage("setAvailable() ENTER", LibraryLoggerType.DEBUG, BookImpl.class);
		this.available = available;
		LibraryLogger.logMessage("setAvailable() LEAVE", LibraryLoggerType.DEBUG, BookImpl.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.Book#toString()
	 */
	@Override
	public String toString() {
		LibraryLogger.logMessage("toString() ENTER", LibraryLoggerType.DEBUG, BookImpl.class);
		LibraryLogger.logMessage("toString() LEAVE", LibraryLoggerType.DEBUG, BookImpl.class);
		return "Book [author=" + author + ", title=" + title + ", id=" + id + ", available=" + available + "]";
	}

}
