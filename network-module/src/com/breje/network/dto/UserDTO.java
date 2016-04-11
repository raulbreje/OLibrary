package com.breje.network.dto;

import java.io.Serializable;

/**
 * 
 * @author Raul Breje
 *
 */
public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2118274699407470915L;
	private String userName;
	private String password;

	public UserDTO(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "UserDTO[" + userName + ' ' + password + "]";
	}
}
