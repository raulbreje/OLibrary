package com.breje.model;

import java.io.Serializable;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;

/**
 * 
 * @author Raul Breje
 *
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2352858999767988246L;
	private int id;
	private String userName;
	private String passwd;
	private String fullName;

	public User(int id, String userName, String passwd, String fullName) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, User.class);
		this.id = id;
		this.userName = userName;
		this.passwd = passwd;
		this.fullName = fullName;
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, User.class);
	}

	public int getId() {
		LibraryLogger.logMessage("getId() ENTER", LibraryLoggerType.DEBUG, User.class);
		LibraryLogger.logMessage("getId() LEAVE", LibraryLoggerType.DEBUG, User.class);
		return id;
	}

	public void setId(int id) {
		LibraryLogger.logMessage("setId() ENTER", LibraryLoggerType.DEBUG, User.class);
		this.id = id;
		LibraryLogger.logMessage("setId() LEAVE", LibraryLoggerType.DEBUG, User.class);
	}

	public String getUserName() {
		LibraryLogger.logMessage("getUserName() ENTER", LibraryLoggerType.DEBUG, User.class);
		LibraryLogger.logMessage("getUserName() LEAVE", LibraryLoggerType.DEBUG, User.class);
		return userName;
	}

	public void setUserName(String userName) {
		LibraryLogger.logMessage("setUserName() ENTER", LibraryLoggerType.DEBUG, User.class);
		this.userName = userName;
		LibraryLogger.logMessage("setUserName() LEAVE", LibraryLoggerType.DEBUG, User.class);
	}

	public String getPasswd() {
		LibraryLogger.logMessage("getPasswd() ENTER", LibraryLoggerType.DEBUG, User.class);
		LibraryLogger.logMessage("getPasswd() LEAVE", LibraryLoggerType.DEBUG, User.class);
		return passwd;
	}

	public void setPasswd(String passwd) {
		LibraryLogger.logMessage("setPasswd() ENTER", LibraryLoggerType.DEBUG, User.class);
		this.passwd = passwd;
		LibraryLogger.logMessage("setPasswd() LEAVE", LibraryLoggerType.DEBUG, User.class);
	}

	public String getFullName() {
		LibraryLogger.logMessage("getFullName() ENTER", LibraryLoggerType.DEBUG, User.class);
		LibraryLogger.logMessage("getFullName() LEAVE", LibraryLoggerType.DEBUG, User.class);
		return fullName;
	}

	public void setFullName(String fullName) {
		LibraryLogger.logMessage("setUserName() ENTER", LibraryLoggerType.DEBUG, User.class);
		this.fullName = fullName;
		LibraryLogger.logMessage("setUserName() LEAVE", LibraryLoggerType.DEBUG, User.class);
	}

	@Override
	public String toString() {
		LibraryLogger.logMessage("toString() ENTER", LibraryLoggerType.DEBUG, User.class);
		LibraryLogger.logMessage("toString() LEAVE", LibraryLoggerType.DEBUG, User.class);
		return "User [id=" + id + ", userName=" + userName + ", passwd=" + passwd + ", fullName=" + fullName + "]";
	}
	
	

}
