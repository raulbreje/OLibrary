package com.breje.model;

import java.io.Serializable;

public interface User extends Serializable {

	int getUserId();

	void setBookId(int id);

	String getUserName();

	void setUserName(String userName);

	String getPasswd();

	void setPasswd(String passwd);

	String getFullName();

	void setFullName(String fullName);

	String toString();

}