package com.breje.model;

import java.io.Serializable;

public interface Book extends Serializable {

	String getAuthor();

	void setAuthor(String author);

	String getTitle();

	void setTitle(String title);

	int getId();

	void setId(int id);

	int getAvailable();

	void setAvailable(int available);

	String toString();

}