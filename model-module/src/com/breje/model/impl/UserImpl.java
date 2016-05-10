package com.breje.model.impl;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.model.User;

/**
 * 
 * @author Raul Breje
 *
 */
public class UserImpl implements User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2352858999767988246L;
	private int id;
	private String userName;
	private String passwd;
	private String fullName;

	public UserImpl(int id, String userName, String passwd, String fullName) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, UserImpl.class);
		this.id = id;
		this.userName = userName;
		this.passwd = passwd;
		this.fullName = fullName;
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, UserImpl.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.User#getId()
	 */
	@Override
	public int getId() {
		LibraryLogger.logMessage("getId() ENTER", LibraryLoggerType.DEBUG, UserImpl.class);
		LibraryLogger.logMessage("getId() LEAVE", LibraryLoggerType.DEBUG, UserImpl.class);
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.User#setId(int)
	 */
	@Override
	public void setId(int id) {
		LibraryLogger.logMessage("setId() ENTER", LibraryLoggerType.DEBUG, UserImpl.class);
		this.id = id;
		LibraryLogger.logMessage("setId() LEAVE", LibraryLoggerType.DEBUG, UserImpl.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.User#getUserName()
	 */
	@Override
	public String getUserName() {
		LibraryLogger.logMessage("getUserName() ENTER", LibraryLoggerType.DEBUG, UserImpl.class);
		LibraryLogger.logMessage("getUserName() LEAVE", LibraryLoggerType.DEBUG, UserImpl.class);
		return userName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.User#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {
		LibraryLogger.logMessage("setUserName() ENTER", LibraryLoggerType.DEBUG, UserImpl.class);
		this.userName = userName;
		LibraryLogger.logMessage("setUserName() LEAVE", LibraryLoggerType.DEBUG, UserImpl.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.User#getPasswd()
	 */
	@Override
	public String getPasswd() {
		LibraryLogger.logMessage("getPasswd() ENTER", LibraryLoggerType.DEBUG, UserImpl.class);
		LibraryLogger.logMessage("getPasswd() LEAVE", LibraryLoggerType.DEBUG, UserImpl.class);
		return passwd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.User#setPasswd(java.lang.String)
	 */
	@Override
	public void setPasswd(String passwd) {
		LibraryLogger.logMessage("setPasswd() ENTER", LibraryLoggerType.DEBUG, UserImpl.class);
		this.passwd = passwd;
		LibraryLogger.logMessage("setPasswd() LEAVE", LibraryLoggerType.DEBUG, UserImpl.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.User#getFullName()
	 */
	@Override
	public String getFullName() {
		LibraryLogger.logMessage("getFullName() ENTER", LibraryLoggerType.DEBUG, UserImpl.class);
		LibraryLogger.logMessage("getFullName() LEAVE", LibraryLoggerType.DEBUG, UserImpl.class);
		return fullName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.User#setFullName(java.lang.String)
	 */
	@Override
	public void setFullName(String fullName) {
		LibraryLogger.logMessage("setUserName() ENTER", LibraryLoggerType.DEBUG, UserImpl.class);
		this.fullName = fullName;
		LibraryLogger.logMessage("setUserName() LEAVE", LibraryLoggerType.DEBUG, UserImpl.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.breje.model.impl.User#toString()
	 */
	@Override
	public String toString() {
		LibraryLogger.logMessage("toString() ENTER", LibraryLoggerType.DEBUG, UserImpl.class);
		LibraryLogger.logMessage("toString() LEAVE", LibraryLoggerType.DEBUG, UserImpl.class);
		return "User [id=" + id + ", userName=" + userName + ", passwd=" + passwd + ", fullName=" + fullName + "]";
	}

}
