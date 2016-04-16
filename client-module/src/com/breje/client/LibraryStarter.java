package com.breje.client;

import com.breje.client.gui.LibraryClientController;
import com.breje.client.gui.LoginWindow;
import com.breje.network.protocols.rpc.LibraryServerRpcProxy;
import com.breje.services.ILibraryServer;

public class LibraryStarter {

	public static void main(String[] args) {
		ILibraryServer server = new LibraryServerRpcProxy("localhost", 60000);
		LibraryClientController libraryClientController = new LibraryClientController(server);
		LoginWindow loginWindow = new LoginWindow("Library", libraryClientController);
		loginWindow.setSize(200, 200);
		loginWindow.setLocation(600, 300);
		loginWindow.setVisible(true);
	}

}
