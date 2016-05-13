package com.breje.client;

import com.breje.client.gui.LibraryClientController;
import com.breje.client.gui.LoginWindow;
import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.protocols.rpc.LibraryServerRpcProxy;
import com.breje.services.ILibraryServer;

public class LibraryStarter {

	public static void main(String[] args) {
		LibraryLogger.logMessage("main() ENTER", LibraryLoggerType.DEBUG, LibraryStarter.class);
		ILibraryServer server = new LibraryServerRpcProxy("localhost", 60000);
		LibraryClientController libraryClientController = new LibraryClientController(server);
		LoginWindow loginWindow = new LoginWindow("OLibrary", libraryClientController);
		loginWindow.setSize(200, 200);
		loginWindow.setLocation(600, 300);
		loginWindow.setVisible(true);
		LibraryLogger.logMessage("main() LEAVE", LibraryLoggerType.DEBUG, LibraryStarter.class);
	}

}
