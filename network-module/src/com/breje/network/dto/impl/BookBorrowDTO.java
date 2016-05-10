package com.breje.network.dto.impl;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.dto.IBookBorrowDTO;

public class BookBorrowDTO implements IBookBorrowDTO {

	private int bookId;
	private int quantity;
	private boolean isCurrentUser;

	public BookBorrowDTO(int bookId, int quantity, boolean isCurrentUser) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
		this.bookId = bookId;
		this.quantity = quantity;
		this.isCurrentUser = isCurrentUser;
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
	}

	@Override
	public int getBookId() {
		LibraryLogger.logMessage("getBookId() ENTER", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
		LibraryLogger.logMessage("getBookId() LEAVE", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
		return bookId;
	}

	@Override
	public void setBookId(int bookId) {
		LibraryLogger.logMessage("setBookId() ENTER", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
		this.bookId = bookId;
		LibraryLogger.logMessage("setBookId() LEAVE", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
	}

	@Override
	public int getQuantity() {
		LibraryLogger.logMessage("getQuantity() ENTER", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
		LibraryLogger.logMessage("getQuantity() LEAVE", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
		return quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		LibraryLogger.logMessage("setQuantity() ENTER", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
		this.quantity = quantity;
		LibraryLogger.logMessage("setQuantity() LEAVE", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
	}

	@Override
	public boolean isByCurrentUser() {
		LibraryLogger.logMessage("isByCurrentUser() ENTER", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
		LibraryLogger.logMessage("isByCurrentUser() LEAVE", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
		return isCurrentUser;
	}

	@Override
	public void setCurrentUser(boolean isCurrentUser) {
		LibraryLogger.logMessage("setCurrentUser() ENTER", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
		this.isCurrentUser = isCurrentUser;
		LibraryLogger.logMessage("setCurrentUser() LEAVE", LibraryLoggerType.DEBUG, BookBorrowDTO.class);
	}

}
