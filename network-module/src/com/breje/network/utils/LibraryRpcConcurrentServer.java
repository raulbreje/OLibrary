package com.breje.network.utils;

import java.net.Socket;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.protocols.rpc.LibraryClientRpcWorker;
import com.breje.services.ILibraryServer;

/**
 * Created by grigo on 2/25/16.
 */
public class LibraryRpcConcurrentServer extends AbstractConcurrentServer {

	private ILibraryServer chatServer;

	public LibraryRpcConcurrentServer(int port, ILibraryServer chatServer) {
		super(port);
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, LibraryRpcConcurrentServer.class);
		this.chatServer = chatServer;
		LibraryLogger.logMessage("Library Concurrent Server has been started with success.", LibraryLoggerType.INFO,
				LibraryRpcConcurrentServer.class);
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, LibraryRpcConcurrentServer.class);
	}

	@Override
	protected Thread createWorker(Socket client) {
		LibraryLogger.logMessage("createWorker() ENTER", LibraryLoggerType.DEBUG, LibraryRpcConcurrentServer.class);
		LibraryClientRpcWorker worker = new LibraryClientRpcWorker(chatServer, client);
		Thread tw = new Thread(worker);
		LibraryLogger.logMessage("createWorker() LEAVE", LibraryLoggerType.DEBUG, LibraryRpcConcurrentServer.class);
		return tw;
	}

}
