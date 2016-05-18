package com.breje.network.dto;

import java.io.Serializable;

public interface IUserDTO extends Serializable {
	String getUserName();

	void setUserName(String userName);

	String getPassword();

	String toString();
}
