package com.breje.network.dto.impl;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.dto.IBookReturnDTO;

public class BookReturnDTO implements IBookReturnDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 992808769851758753L;
	private int bookId;
	private String author;
	private String title;
	private boolean isCurrentUser;

	public BookReturnDTO(int bookId, String author, String title, boolean isCurrentUser) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		this.bookId = bookId;
		this.author = author;
		this.title = title;
		this.isCurrentUser = isCurrentUser;
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, BookReturnDTO.class);
	}

	@Override
	public int getBookId() {
		LibraryLogger.logMessage("getBookId() ENTER", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		LibraryLogger.logMessage("getBookId() LEAVE", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		return bookId;
	}

	@Override
	public void setBookId(int bookId) {
		LibraryLogger.logMessage("setBookId() ENTER", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		this.bookId = bookId;
		LibraryLogger.logMessage("setBookId() LEAVE", LibraryLoggerType.DEBUG, BookReturnDTO.class);
	}

	@Override
	public String getAuthor() {
		LibraryLogger.logMessage("getAuthor() ENTER", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		LibraryLogger.logMessage("getAuthor() LEAVE", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		return author;
	}

	@Override
	public void setAuthor(String author) {
		LibraryLogger.logMessage("setAuthor() ENTER", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		this.author = author;
		LibraryLogger.logMessage("setAuthor() LEAVE", LibraryLoggerType.DEBUG, BookReturnDTO.class);
	}

	@Override
	public String getTitle() {
		LibraryLogger.logMessage("getTitle() ENTER", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		LibraryLogger.logMessage("getTitle() LEAVE", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		return title;
	}

	@Override
	public void setTitle(String title) {
		LibraryLogger.logMessage("setTitle() ENTER", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		this.title = title;
		LibraryLogger.logMessage("setTitle() LEAVE", LibraryLoggerType.DEBUG, BookReturnDTO.class);
	}

	@Override
	public boolean isByCurrentUser() {
		LibraryLogger.logMessage("isByCurrentUser() ENTER", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		LibraryLogger.logMessage("isByCurrentUser() LEAVE", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		return isCurrentUser;
	}

	@Override
	public void setCurrentUser(boolean isCurrentUser) {
		LibraryLogger.logMessage("setCurrentUser() ENTER", LibraryLoggerType.DEBUG, BookReturnDTO.class);
		this.isCurrentUser = isCurrentUser;
		LibraryLogger.logMessage("setCurrentUser() LEAVE", LibraryLoggerType.DEBUG, BookReturnDTO.class);
	}

}
