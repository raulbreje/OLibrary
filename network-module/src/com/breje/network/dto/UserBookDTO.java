package com.breje.network.dto;

import java.io.Serializable;

/**
 * 
 * @author Raul Breje
 *
 */
public class UserBookDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8641310340441552540L;
	private int userId;
	private int bookId;

	public UserBookDTO(int userId, int bookId) {
		this.userId = userId;
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
}
