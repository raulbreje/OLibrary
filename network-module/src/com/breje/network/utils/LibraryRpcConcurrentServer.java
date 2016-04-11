package com.breje.network.utils;

import java.net.Socket;

import com.breje.network.protocols.rpc.LibraryClientRpcWorker;
import com.breje.services.ILibraryServer;

/**
 * Created by grigo on 2/25/16.
 */
public class LibraryRpcConcurrentServer extends AbstractConcurrentServer {

	private ILibraryServer chatServer;

	public LibraryRpcConcurrentServer(int port, ILibraryServer chatServer) {
		super(port);
		this.chatServer = chatServer;
		System.out.println("Chat- LibraryRpcConcurrentServer");
	}

	@Override
	protected Thread createWorker(Socket client) {
		LibraryClientRpcWorker worker = new LibraryClientRpcWorker(chatServer, client);
		Thread tw = new Thread(worker);
		return tw;
	}

}
