package com.breje.network.dto.impl;

import java.io.Serializable;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.dto.IBookDTO;

/**
 * 
 * @author Raul Breje
 *
 */
@Deprecated
public class BookDTO implements Serializable, IBookDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8997346877162126945L;
	private int id;
	private String author, title;

	@Deprecated
	public BookDTO(int id, String author, String title) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, BookDTO.class);
		this.id = id;
		this.author = author;
		this.title = title;
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, BookDTO.class);
	}

	@Deprecated
	public int getId() {
		LibraryLogger.logMessage("getId() ENTER", LibraryLoggerType.DEBUG, BookDTO.class);
		LibraryLogger.logMessage("getId() LEAVE", LibraryLoggerType.DEBUG, BookDTO.class);
		return id;
	}

	@Deprecated
	public void setId(int id) {
		LibraryLogger.logMessage("setId() ENTER", LibraryLoggerType.DEBUG, BookDTO.class);
		this.id = id;
		LibraryLogger.logMessage("setId() LEAVE", LibraryLoggerType.DEBUG, BookDTO.class);
	}

	@Deprecated
	public String getAuthor() {
		LibraryLogger.logMessage("getAuthor() ENTER", LibraryLoggerType.DEBUG, BookDTO.class);
		LibraryLogger.logMessage("getAuthor() LEAVE", LibraryLoggerType.DEBUG, BookDTO.class);
		return author;
	}

	@Deprecated
	public void setAuthor(String author) {
		LibraryLogger.logMessage("setAuthor() ENTER", LibraryLoggerType.DEBUG, BookDTO.class);
		this.author = author;
		LibraryLogger.logMessage("setAuthor() LEAVE", LibraryLoggerType.DEBUG, BookDTO.class);
	}

	@Deprecated
	public String getTitle() {
		LibraryLogger.logMessage("getTitle() ENTER", LibraryLoggerType.DEBUG, BookDTO.class);
		LibraryLogger.logMessage("getTitle() LEAVE", LibraryLoggerType.DEBUG, BookDTO.class);
		return title;
	}

	@Deprecated
	public void setTitle(String title) {
		LibraryLogger.logMessage("setTitle() ENTER", LibraryLoggerType.DEBUG, BookDTO.class);
		this.title = title;
		LibraryLogger.logMessage("setTitle() LEAVE", LibraryLoggerType.DEBUG, BookDTO.class);
	}
}
