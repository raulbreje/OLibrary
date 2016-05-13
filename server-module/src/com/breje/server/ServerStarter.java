package com.breje.server;

import com.breje.network.exceptions.ServerException;
import com.breje.network.utils.AbstractServer;
import com.breje.network.utils.LibraryRpcConcurrentServer;
import com.breje.server.xml.XmlParser;
import com.breje.services.ILibraryServer;
import com.breje.services.impl.LibraryServerImpl;

public class ServerStarter {
	public static void main(String[] args) {
		XmlParser.getConfig();
		ILibraryServer libraryServer = new LibraryServerImpl();
		AbstractServer server = new LibraryRpcConcurrentServer(60000, libraryServer);
		try {
			server.start();
		} catch (ServerException e) {
			System.out.println(e.getMessage());
		}
	}
}
