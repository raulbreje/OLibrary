package com.breje.network.dto;

import java.io.Serializable;

/**
 * 
 * @author Raul Breje
 *
 */
public class BookQuantityDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 130906087187627346L;
	private int bookId;
	private int newQuantity;

	public BookQuantityDTO(int bookId, int newQuantity) {
		this.bookId = bookId;
		this.newQuantity = newQuantity;
	}

	public int getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(int newQuantity) {
		this.newQuantity = newQuantity;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
}
