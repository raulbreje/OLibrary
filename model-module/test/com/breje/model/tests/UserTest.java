package com.breje.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.breje.model.User;
import com.breje.model.impl.UserImpl;

public class UserTest {

	@Test
	public void testUser() {
		try {
			new UserImpl(0, "user1", "password1", "User Name");
			new UserImpl(1, "user2", "password2", "User Name2");
			new UserImpl(2, "user3", "password3", "User Name3");
			new UserImpl(3, "user4", "password4", "User Name4");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetId() {
		User user = new UserImpl(0, "user1", "password1", "User Name");
		assertEquals(0, user.getUserId());
		user = new UserImpl(3, "user1", "password1", "User Name");
		assertEquals(3, user.getUserId());
	}

	@Test
	public void testSetId() {
		User user = new UserImpl(0, "user1", "password1", "User Name");
		try {
			user.setBookId(1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals(1, user.getUserId());
		user = new UserImpl(3, "user1", "password1", "User Name");
		try {
			user.setBookId(2);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals(2, user.getUserId());
	}

	@Test
	public void testGetUserName() {
		User user = new UserImpl(0, "user1", "password1", "User Name");
		assertEquals("user1", user.getUserName());
		user = new UserImpl(3, "user12", "password12", "User Name1");
		assertEquals("user12", user.getUserName());
	}

	@Test
	public void testSetUserName() {
		User user = new UserImpl(0, "user1", "password1", "User Name");
		try {
			user.setUserName("user2");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals("user2", user.getUserName());
		user = new UserImpl(3, "user1", "password1", "User Name");
		try {
			user.setUserName("user3");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals("user3", user.getUserName());
	}

	@Test
	public void testGetPasswd() {
		User user = new UserImpl(0, "user1", "password1", "User Name");
		assertEquals("password1", user.getPasswd());
		user = new UserImpl(3, "user12", "password12", "User Name1");
		assertEquals("password12", user.getPasswd());
	}

	@Test
	public void testSetPasswd() {
		User user = new UserImpl(0, "user1", "password1", "User Name");
		try {
			user.setPasswd("pass2");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals("pass2", user.getPasswd());
		user = new UserImpl(3, "user1", "password1", "User Name");
		try {
			user.setPasswd("user3");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals("user3", user.getPasswd());
	}

	@Test
	public void testGetFullName() {
		User user = new UserImpl(0, "user1", "password1", "User Name");
		assertEquals("User Name", user.getFullName());
		user = new UserImpl(3, "user12", "password12", "User Name1");
		assertEquals("User Name1", user.getFullName());
	}

	@Test
	public void testSetFullName() {
		User user = new UserImpl(0, "user1", "password1", "User Name");
		try {
			user.setFullName("Name User");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals("Name User", user.getFullName());
		user = new UserImpl(3, "user1", "password1", "User Name");
		try {
			user.setFullName("user3");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals("user3", user.getFullName());
	}

	@Test
	public void testToString() {
		User user = new UserImpl(0, "user1", "password1", "User Name");
		String resultString = "User [id=0, userName=user1, passwd=password1, fullName=User Name]";
		assertEquals(resultString, user.toString());
	}

}
