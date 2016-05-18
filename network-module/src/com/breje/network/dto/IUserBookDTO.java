package com.breje.network.dto;

import java.io.Serializable;

public interface IUserBookDTO extends Serializable {
	int getUserId();

	void setUserId(int userId);

	int getBookId();

	void setBookId(int bookId);
}
