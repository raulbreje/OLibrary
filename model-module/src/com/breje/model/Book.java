package com.breje.model;

import java.io.Serializable;

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
	private String author;
	private String title;
	private int id;
	private int available;

	public Book(int id, String author, String title, int available) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.available = available;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", title=" + title + ", id=" + id + ", available=" + available + "]";
	}

}
