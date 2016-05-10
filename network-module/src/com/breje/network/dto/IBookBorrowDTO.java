package com.breje.network.dto;

public interface IBookBorrowDTO {
	int getBookId();

	void setBookId(int bookId);

	int getQuantity();

	void setQuantity(int quantity);

	boolean isByCurrentUser();

	void setCurrentUser(boolean isCurrentUser);

}
