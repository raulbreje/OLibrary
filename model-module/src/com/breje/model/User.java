package com.breje.model;

import java.io.Serializable;

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
		this.id = id;
		this.userName = userName;
		this.passwd = passwd;
		this.fullName = fullName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", passwd=" + passwd + ", fullName=" + fullName + "]";
	}
	
	

}
