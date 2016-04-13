package com.breje.persistence.utils;

import java.text.MessageFormat;

public class SQLUtils {

	public static MessageFormat LOGIN_SQL = new MessageFormat(
			"SELECT * FROM USER_TBL WHERE user_name={0} AND passwd={1}");

	public static MessageFormat SELECT_AVAILABLE_BOOKS_SQL = new MessageFormat(
			"SELECT * FROM BOOK_TBL WHERE availability>0");

	public static MessageFormat SELECT_USER_BOOKS_SQL = new MessageFormat(
			"SELECT * FROM BOOK_TBL b INNER JOIN BORROW_TBL bo ON bo.book_id=b.book_id INNER JOIN USER_TBL u ON u.user_id=bo.user_id WHERE u.user_id={0}");

	public static MessageFormat SEARCH_BOOKS_SQL = new MessageFormat(
			"SELECT * FROM BOOK_TBL WHERE title LIKE {0}");

	public static String toQuotedString(String target) {
		return "'" + target + "'";
	}
}
