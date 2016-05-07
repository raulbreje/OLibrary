package com.breje.network.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.exceptions.ServerException;

/**
 * Created by IntelliJ IDEA. User: grigo Date: Mar 18, 2009 Time: 11:41:16 AM
 */
public abstract class AbstractServer {
	private int port;
	private ServerSocket server = null;

	public AbstractServer(int port) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, AbstractServer.class);
		this.port = port;
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, AbstractServer.class);
	}

	public void start() throws ServerException {
		LibraryLogger.logMessage("start() ENTER", LibraryLoggerType.DEBUG, AbstractServer.class);
		try {
			server = new ServerSocket(port);
			while (true) {
				LibraryLogger.logMessage("Waiting for clients ...", LibraryLoggerType.INFO, AbstractServer.class);
				Socket client = server.accept();
				LibraryLogger.logMessage("Client connected ...", LibraryLoggerType.INFO, AbstractServer.class);
				processRequest(client);
			}
		} catch (IOException e) {
			LibraryLogger.logMessage("An error occurs in the starting process of the server.\n" + e,
					LibraryLoggerType.ERROR, AbstractServer.class);
			throw new ServerException("Server error.", e);
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				LibraryLogger.logMessage("An error occurs in the stopping process of the server.\n" + e,
						LibraryLoggerType.ERROR, AbstractServer.class);
				throw new ServerException("Closing server error ", e);
			}
			LibraryLogger.logMessage("start() LEAVE", LibraryLoggerType.DEBUG, AbstractServer.class);
		}
	}

	protected abstract void processRequest(Socket client);

	public void stop() throws ServerException {
		LibraryLogger.logMessage("stop() ENTER", LibraryLoggerType.DEBUG, AbstractServer.class);
		try {
			server.close();
		} catch (IOException e) {
			throw new ServerException("Closing server error ", e);
		}
		LibraryLogger.logMessage("stop() ENTER", LibraryLoggerType.DEBUG, AbstractServer.class);
	}
}
