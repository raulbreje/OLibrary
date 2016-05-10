package com.breje.network.dto.impl;

import java.io.Serializable;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.dto.IBookQuantityDTO;

/**
 * 
 * @author Raul Breje
 *
 */
@Deprecated
public class BookQuantityDTO implements Serializable, IBookQuantityDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130906087187627346L;
	private int bookId;
	private int newQuantity;

	public BookQuantityDTO(int bookId, int newQuantity) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, BookQuantityDTO.class);
		this.bookId = bookId;
		this.newQuantity = newQuantity;
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, BookQuantityDTO.class);
	}

	public int getNewQuantity() {
		LibraryLogger.logMessage("getNewQuantity() ENTER", LibraryLoggerType.DEBUG, BookQuantityDTO.class);
		LibraryLogger.logMessage("getNewQuantity() LEAVE", LibraryLoggerType.DEBUG, BookQuantityDTO.class);
		return newQuantity;
	}

	public void setNewQuantity(int newQuantity) {
		LibraryLogger.logMessage("setNewQuantity() ENTER", LibraryLoggerType.DEBUG, BookQuantityDTO.class);
		this.newQuantity = newQuantity;
		LibraryLogger.logMessage("setNewQuantity() LEAVE", LibraryLoggerType.DEBUG, BookQuantityDTO.class);
	}

	public int getBookId() {
		LibraryLogger.logMessage("getBookId() ENTER", LibraryLoggerType.DEBUG, BookQuantityDTO.class);
		LibraryLogger.logMessage("getBookId() LEAVE", LibraryLoggerType.DEBUG, BookQuantityDTO.class);
		return bookId;
	}

	public void setBookId(int bookId) {
		LibraryLogger.logMessage("setBookId() ENTER", LibraryLoggerType.DEBUG, BookQuantityDTO.class);
		this.bookId = bookId;
		LibraryLogger.logMessage("setBookId() LEAVE", LibraryLoggerType.DEBUG, BookQuantityDTO.class);
	}
}
