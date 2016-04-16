package com.breje.common.logging;

import org.apache.log4j.Logger;

public class LibraryLogger {

	private static final Logger LOGGER = Logger.getLogger(LibraryLogger.class);

	@Deprecated
	public static void logMessage(String message, LibraryLoggerType type) {
		switch (type) {
		case INFO:
			LOGGER.info(message);
			break;
		case DEBUG:
			LOGGER.debug(message);
			break;
		case WARN:
			LOGGER.warn(message);
			break;
		case ERROR:
			LOGGER.error(message);
			break;
		case FATAL:
			LOGGER.fatal(message);
			break;
		default:
			LOGGER.warn("Unexpected logging type");
			break;
		}
	}

	public static void logMessage(String message, LibraryLoggerType type, Class clazz) {
		Logger logger = Logger.getLogger(clazz.getName());
		switch (type) {
		case INFO:
			logger.info(message);
			break;
		case DEBUG:
			logger.debug(message);
			break;
		case WARN:
			logger.warn(message);
			break;
		case ERROR:
			logger.error(message);
			break;
		case FATAL:
			logger.fatal(message);
			break;
		default:
			logger.warn("Unexpected logging type");
			break;
		}
	}
}
