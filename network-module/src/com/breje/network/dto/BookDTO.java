package com.breje.network.dto;

import java.io.Serializable;

/**
 * 
 * @author Raul Breje
 *
 */
public class BookDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8997346877162126945L;
	private int id;
	private String author, title;

	public BookDTO(int id, String author, String title) {
		this.id = id;
		this.author = author;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
