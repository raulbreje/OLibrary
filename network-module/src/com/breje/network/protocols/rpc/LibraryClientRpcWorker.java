package com.breje.network.protocols.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.exceptions.LibraryException;
import com.breje.model.Book;
import com.breje.model.User;
import com.breje.network.dto.IBookDTO;
import com.breje.network.dto.IBookQuantityDTO;
import com.breje.network.dto.IUserBookDTO;
import com.breje.network.dto.IUserDTO;
import com.breje.network.dto.impl.BookDTO;
import com.breje.network.dto.impl.BookQuantityDTO;
import com.breje.services.ILibraryClient;
import com.breje.services.ILibraryServer;

/**
 * Created by grigo on 12/15/15.
 */
public class LibraryClientRpcWorker implements Runnable, ILibraryClient {

	private ILibraryServer server;
	private Socket connection;

	private ObjectInputStream input;
	private ObjectOutputStream output;
	private volatile boolean connected;

	public LibraryClientRpcWorker(ILibraryServer server, Socket connection) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		this.server = server;
		this.connection = connection;
		try {
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
			input = new ObjectInputStream(connection.getInputStream());
			connected = true;
			LibraryLogger.logMessage("Connection has been estabilished with the server.", LibraryLoggerType.INFO,
					LibraryClientRpcWorker.class);
		} catch (IOException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryClientRpcWorker.class);
		}
		LibraryLogger.logMessage("contructor LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
	}

	public void run() {
		LibraryLogger.logMessage("run() ENTER", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		while (connected) {
			try {
				Object request = input.readObject();
				LibraryLogger.logMessage("Request has been received.\n" + request, LibraryLoggerType.INFO,
						LibraryClientRpcWorker.class);
				Response response = handleRequest((Request) request);
				if (response != null) {
					sendResponse(response);
				}
			} catch (IOException e) {
				LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryClientRpcWorker.class);
			} catch (ClassNotFoundException e) {
				LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryClientRpcWorker.class);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryClientRpcWorker.class);
			}
		}
		try {
			input.close();
			output.close();
			connection.close();
		} catch (IOException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryClientRpcWorker.class);
		}
		LibraryLogger.logMessage("run() LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
	}

	@Override
	public void bookUpdated(int bookId, int newQuantity) throws LibraryException {
		LibraryLogger.logMessage("bookUpdated() ENTER", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		IBookQuantityDTO bookQuantityDTO = new BookQuantityDTO(bookId, newQuantity);
		Response response = new Response.Builder().type(ResponseType.BORROW_BOOK).data(bookQuantityDTO).build();
		try {
			sendResponse(response);
		} catch (IOException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryClientRpcWorker.class);
			throw new LibraryException("Error occurs when trying to send response. Contact your administrator.");
		}
		LibraryLogger.logMessage("bookUpdated() LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
	}

	@Override
	public void bookReturned(int bookId, String author, String title) throws LibraryException {
		LibraryLogger.logMessage("bookReturned() ENTER", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		IBookDTO bookDTO = new BookDTO(bookId, author, title);
		Response response = new Response.Builder().type(ResponseType.RETURN_BOOK).data(bookDTO).build();
		try {
			sendResponse(response);
		} catch (IOException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryClientRpcWorker.class);
			throw new LibraryException("Error occurs when trying to send response. Contact your administrator.");
		}
		LibraryLogger.logMessage("bookReturned() LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
	}

	private Response handleRequest(Request request) {
		LibraryLogger.logMessage("handleRequest() ENTER", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		Response response = null;
		if (request.type() == RequestType.LOGIN) {
			LibraryLogger.logMessage("Receiving login request...", LibraryLoggerType.INFO,
					LibraryClientRpcWorker.class);
			IUserDTO userDTO = (IUserDTO) request.data();
			try {
				User user = server.login(userDTO.getUserName(), userDTO.getPassword(), this);
				return new Response.Builder().type(ResponseType.OK).data(user).build();
			} catch (LibraryException e) {
				connected = false;
				return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
			}
		}
		if (request.type() == RequestType.LOGOUT) {
			LibraryLogger.logMessage("Receiving logout request...", LibraryLoggerType.INFO,
					LibraryClientRpcWorker.class);
			int userId = (int) request.data();
			try {
				server.logout(userId, this);
				connected = false;
				return new Response.Builder().type(ResponseType.OK).build();
			} catch (LibraryException e) {
				return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
			}
		}
		if (request.type() == RequestType.GET_AVAILABLE_BOOKS) {
			try {
				List<Book> allBooks = server.getAvailableBooks();
				return new Response.Builder().type(ResponseType.GET_AVAILABLE_BOOKS).data(allBooks).build();
			} catch (LibraryException e) {
				return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
			}
		}
		if (request.type() == RequestType.GET_USER_BOOKS) {
			try {
				int userId = (int) request.data();
				List<Book> userBooks = server.getUserBooks(userId);
				return new Response.Builder().type(ResponseType.GET_USER_BOOKS).data(userBooks).build();
			} catch (LibraryException e) {
				return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
			}
		}
		if (request.type() == RequestType.SEARCH_BOOKS) {
			try {
				String key = (String) request.data();
				List<Book> foundBooks = server.searchBooks(key);
				return new Response.Builder().type(ResponseType.GET_USER_BOOKS).data(foundBooks).build();
			} catch (LibraryException e) {
				return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
			}
		}
		if (request.type() == RequestType.BORROW_BOOK) {
			try {
				IUserBookDTO userBookDTO = (IUserBookDTO) request.data();
				server.borrowBook(userBookDTO.getUserId(), userBookDTO.getBookId());
				return new Response.Builder().type(ResponseType.OK).build();
			} catch (LibraryException e) {
				return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
			}
		}
		if (request.type() == RequestType.RETURN_BOOK) {
			try {
				IUserBookDTO userBookDTO = (IUserBookDTO) request.data();
				server.returnBook(userBookDTO.getUserId(), userBookDTO.getBookId());
				return new Response.Builder().type(ResponseType.OK).build();
			} catch (LibraryException e) {
				return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
			}
		}
		LibraryLogger.logMessage("handleRequest() LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		return response;
	}

	private void sendResponse(Response response) throws IOException {
		LibraryLogger.logMessage("sendResponse() ENTER", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		LibraryLogger.logMessage("Sending response...", LibraryLoggerType.INFO, LibraryClientRpcWorker.class);
		output.writeObject(response);
		output.flush();
		LibraryLogger.logMessage("Response has been sent.\n" + response, LibraryLoggerType.INFO,
				LibraryClientRpcWorker.class);
		LibraryLogger.logMessage("sendResponse() LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
	}

}
