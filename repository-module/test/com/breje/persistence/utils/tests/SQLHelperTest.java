package com.breje.persistence.utils.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.breje.persistence.utils.SQLHelper;

public class SQLHelperTest {

	@Test
	public void testToQuotedString() {
		String test = "doi";
		String target = "'doi'";
		String result = SQLHelper.toQuotedString(test);
		assertEquals(target, result);
	}

}
