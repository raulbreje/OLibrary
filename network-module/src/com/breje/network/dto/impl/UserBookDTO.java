package com.breje.network.dto.impl;

import java.io.Serializable;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.dto.IUserBookDTO;

/**
 * 
 * @author Raul Breje
 *
 */
public class UserBookDTO implements Serializable, IUserBookDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8641310340441552540L;
	private int userId;
	private int bookId;

	public UserBookDTO(int userId, int bookId) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, UserBookDTO.class);
		this.userId = userId;
		this.bookId = bookId;
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, UserBookDTO.class);
	}

	public int getUserId() {
		LibraryLogger.logMessage("getUserId() ENTER", LibraryLoggerType.DEBUG, UserBookDTO.class);
		LibraryLogger.logMessage("getUserId() LEAVE", LibraryLoggerType.DEBUG, UserBookDTO.class);
		return userId;
	}

	public void setUserId(int userId) {
		LibraryLogger.logMessage("setUserId() ENTER", LibraryLoggerType.DEBUG, UserBookDTO.class);
		this.userId = userId;
		LibraryLogger.logMessage("setUserId() LEAVE", LibraryLoggerType.DEBUG, UserBookDTO.class);
	}

	public int getBookId() {
		LibraryLogger.logMessage("getBookId() ENTER", LibraryLoggerType.DEBUG, UserBookDTO.class);
		LibraryLogger.logMessage("getBookId() LEAVE", LibraryLoggerType.DEBUG, UserBookDTO.class);
		return bookId;
	}

	public void setBookId(int bookId) {
		LibraryLogger.logMessage("setBookId() ENTER", LibraryLoggerType.DEBUG, UserBookDTO.class);
		this.bookId = bookId;
		LibraryLogger.logMessage("setBookId() LEAVE", LibraryLoggerType.DEBUG, UserBookDTO.class);
	}
}
