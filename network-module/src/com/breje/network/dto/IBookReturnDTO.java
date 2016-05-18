package com.breje.network.dto;

import java.io.Serializable;

public interface IBookReturnDTO extends Serializable {
	int getBookId();

	void setBookId(int bookId);

	String getAuthor();

	void setAuthor(String author);

	String getTitle();

	void setTitle(String title);

	boolean isByCurrentUser();

	void setCurrentUser(boolean isCurrentUser);
}
