package com.breje.network.dto;

import java.io.Serializable;

public interface IBookBorrowDTO extends Serializable {
	int getBookId();

	void setBookId(int bookId);

	int getQuantity();

	void setQuantity(int quantity);

	boolean isByCurrentUser();

	void setCurrentUser(boolean isCurrentUser);

}
