package com.breje.persistence.utils;

import java.text.MessageFormat;

public class SQLUtils {

	public static MessageFormat LOGIN_SQL = new MessageFormat("SELECT * FROM USER_TBL WHERE user_name=\'{0}\' AND passwd=\'{1}\'");
	
	
}
