package com.breje.network.utils;

import java.net.Socket;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;

/**
 * Created by IntelliJ IDEA. User: grigo Date: Mar 18, 2009 Time: 12:04:38 PM
 */
public abstract class AbstractConcurrentServer extends AbstractServer {

	public AbstractConcurrentServer(int port) {
		super(port);
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, AbstractConcurrentServer.class);
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, AbstractConcurrentServer.class);
	}

	protected void processRequest(Socket client) {
		LibraryLogger.logMessage("processRequest() ENTER", LibraryLoggerType.DEBUG, AbstractConcurrentServer.class);
		Thread tw = createWorker(client);
		tw.start();
		LibraryLogger.logMessage("processRequest() LEAVE", LibraryLoggerType.DEBUG, AbstractConcurrentServer.class);
	}

	protected abstract Thread createWorker(Socket client);

}
