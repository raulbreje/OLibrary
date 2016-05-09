package com.breje.network.dto.impl;

import java.io.Serializable;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.dto.IUserDTO;

/**
 * 
 * @author Raul Breje
 *
 */
public class UserDTO implements Serializable, IUserDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2118274699407470915L;
	private String userName;
	private String password;

	public UserDTO(String userName, String password) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, UserDTO.class);
		this.userName = userName;
		this.password = password;
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, UserDTO.class);
	}

	public String getUserName() {
		LibraryLogger.logMessage("getUserName() ENTER", LibraryLoggerType.DEBUG, UserDTO.class);
		LibraryLogger.logMessage("getUserName() LEAVE", LibraryLoggerType.DEBUG, UserDTO.class);
		return userName;
	}

	public void setUserName(String userName) {
		LibraryLogger.logMessage("setUserName() ENTER", LibraryLoggerType.DEBUG, UserDTO.class);
		this.userName = userName;
		LibraryLogger.logMessage("setUserName() LEAVE", LibraryLoggerType.DEBUG, UserDTO.class);
	}

	public String getPassword() {
		LibraryLogger.logMessage("getPassword() ENTER", LibraryLoggerType.DEBUG, UserDTO.class);
		LibraryLogger.logMessage("getPassword() LEAVE", LibraryLoggerType.DEBUG, UserDTO.class);
		return password;
	}

	@Override
	public String toString() {
		LibraryLogger.logMessage("toString() ENTER", LibraryLoggerType.DEBUG, UserDTO.class);
		LibraryLogger.logMessage("toString() LEAVE", LibraryLoggerType.DEBUG, UserDTO.class);
		return "UserDTO[" + userName + ' ' + password + "]";
	}
}
