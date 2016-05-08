package com.breje.common.logging.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;

public class LibraryLoggerTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testLogMessage() {
		String message = "first message";
		LibraryLogger.logMessage(message, LibraryLoggerType.INFO);
		message = "second message";
		LibraryLogger.logMessage(message, LibraryLoggerType.WARN);
		message = "third message";
		LibraryLogger.logMessage(message, LibraryLoggerType.DEBUG);
		message = "fourth message";
		LibraryLogger.logMessage(message, LibraryLoggerType.ERROR);
		message = "fifth message";
		LibraryLogger.logMessage(message, LibraryLoggerType.FATAL);
		assertTrue(true);
	}

}
