package com.breje.persistence.utils;

import java.text.MessageFormat;
import java.util.StringJoiner;

public class SQLHelper {

	public static String BORROW_TABLE = "BORROW_TBL";
	public static String BOOK_TABLE = "BOOK_TBL";
	public static String BOOK_ID = "book_id";
	public static String BOOK_TITLE = "title";
	public static String BOOK_AUTHOR = "author";
	public static String BOOK_AVAILABILITY = "availability";
	public static String USER_TABLE = "USER_TBL";
	public static String USER_ID = "user_id";
	public static String USER_PASSWD = "passwd";
	public static String USER_NAME = "user_name";
	public static String USER_FULL_NAME = "full_name";

	public static MessageFormat LOGIN_SQL = new MessageFormat(
			"SELECT * FROM USER_TBL WHERE user_name={0} AND passwd={1}");

	public static String SELECT_AVAILABLE_BOOKS = "SELECT * FROM BOOK_TBL WHERE availability>0";

	public static MessageFormat SELECT_USER_BOOKS_SQL = new MessageFormat(
			"SELECT * FROM BOOK_TBL b INNER JOIN BORROW_TBL bo ON bo.book_id=b.book_id INNER JOIN USER_TBL u ON u.user_id=bo.user_id WHERE u.user_id={0}");

	public static MessageFormat SEARCH_BOOKS_BY_SQL = new MessageFormat("SELECT * FROM BOOK_TBL WHERE {0} LIKE {1}");

	public static MessageFormat SET_AVAILABILITY_BOOK_SQL = new MessageFormat(
			"UPDATE BOOK_TBL SET availability = availability{0} WHERE book_id={1}");

	public static MessageFormat INSERT_BORROW_SQL = new MessageFormat(
			"INSERT INTO BORROW_TBL (user_id, book_id) VALUES ( {0}, {1})");

	public static MessageFormat GET_AVAILABILITY_OF_BOOK_SQL = new MessageFormat(
			"SELECT available FROM BOOK_TBL WHERE book_id={0}");

	public static MessageFormat REMOVE_BORROW_SQL = new MessageFormat(
			"DELETE FROM BORROW_TBL WHERE user_id={0} and book_id={1}");

	public static MessageFormat GET_BOOK_SQL = new MessageFormat("SELECT * FROM BOOK_TBL WHERE book_id={0}");

	@Deprecated
	public static MessageFormat SEARCH_BOOKS_SQL = new MessageFormat("SELECT * FROM BOOK_TBL WHERE title LIKE {0}");

	@Deprecated
	public static MessageFormat DEC_AVAILABILITY_BOOK_SQL = new MessageFormat(
			"UPDATE BOOK_TBL SET availability = availability-1 WHERE book_id={0}");

	@Deprecated
	public static MessageFormat INC_AVAILABILITY_BOOK_SQL = new MessageFormat(
			"UPDATE BOOK_TBL SET availability = availability+1 WHERE book_id={0}");

	public static String toQuotedString(String target) {
		StringJoiner sj = new StringJoiner("", "'", "'");
		return sj.add(target).toString();
	}
}
