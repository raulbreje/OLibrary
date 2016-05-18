package com.breje.network.protocols.rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.exceptions.LibraryException;
import com.breje.model.Book;
import com.breje.model.User;
import com.breje.network.dto.IBookBorrowDTO;
import com.breje.network.dto.IBookDTO;
import com.breje.network.dto.IBookQuantityDTO;
import com.breje.network.dto.IBookReturnDTO;
import com.breje.network.dto.IUserBookDTO;
import com.breje.network.dto.IUserDTO;
import com.breje.network.dto.impl.BookBorrowDTO;
import com.breje.network.dto.impl.BookDTO;
import com.breje.network.dto.impl.BookQuantityDTO;
import com.breje.network.dto.impl.BookReturnDTO;
import com.breje.network.json.RequestDeserializer;
import com.breje.services.ILibraryClient;
import com.breje.services.ILibraryServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by grigo on 12/15/15.
 */
@SuppressWarnings("deprecation")
public class LibraryClientRpcWorker implements Runnable, ILibraryClient {

	private ILibraryServer server;
	private Socket connection;

	private BufferedReader jsonInput;
	private PrintWriter jsonOutput;

	@SuppressWarnings("unused")
	@Deprecated
	private ObjectInputStream input;
	@Deprecated
	private ObjectOutputStream output;
	private volatile boolean connected;

	public LibraryClientRpcWorker(ILibraryServer server, Socket connection) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		this.server = server;
		this.connection = connection;
		try {
			jsonOutput = new PrintWriter(connection.getOutputStream(), true);
			jsonOutput.flush();
			jsonInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			connected = true;
			LibraryLogger.logMessage("Connection has been estabilished with the server.", LibraryLoggerType.INFO,
					LibraryClientRpcWorker.class);
		} catch (IOException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryClientRpcWorker.class);
		}
		LibraryLogger.logMessage("contructor LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
	}

	public void run() {
		while (connected) {
			try {
				String requestJson = jsonInput.readLine();
				if (requestJson != null) {
					GsonBuilder gsonBuilder = new GsonBuilder();
					gsonBuilder.registerTypeAdapter(Request.class, new RequestDeserializer());
					Gson gson = gsonBuilder.create();
					Request request = gson.fromJson(requestJson, Request.class);
					LibraryLogger.logMessage("Request receiver: " + request, LibraryLoggerType.INFO,
							LibraryClientRpcWorker.class);
					Response response = handleRequest(request);
					if (response != null) {
						sendJsonResponse(response);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			jsonInput.close();
			jsonOutput.close();
			connection.close();
		} catch (IOException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryClientRpcWorker.class);
		}
	}

	// public void run() {
	// LibraryLogger.logMessage("run() ENTER", LibraryLoggerType.DEBUG,
	// LibraryClientRpcWorker.class);
	// while (connected) {
	// try {
	// // Object request = input.readObject();
	// Object request = jsonInput.readObject();
	// LibraryLogger.logMessage("Request has been received.\n" + request,
	// LibraryLoggerType.INFO,
	// LibraryClientRpcWorker.class);
	// Response response = handleRequest((Request) request);
	// if (response != null) {
	// sendResponse(response);
	// }
	// } catch (IOException e) {
	// LibraryLogger.logMessage(e, LibraryLoggerType.ERROR,
	// LibraryClientRpcWorker.class);
	// } catch (ClassNotFoundException e) {
	// LibraryLogger.logMessage(e, LibraryLoggerType.ERROR,
	// LibraryClientRpcWorker.class);
	// }
	// try {
	// Thread.sleep(1000);
	// } catch (InterruptedException e) {
	// LibraryLogger.logMessage(e, LibraryLoggerType.ERROR,
	// LibraryClientRpcWorker.class);
	// }
	// }
	// try {
	// input.close();
	// output.close();
	// connection.close();
	// } catch (IOException e) {
	// LibraryLogger.logMessage(e, LibraryLoggerType.ERROR,
	// LibraryClientRpcWorker.class);
	// }
	// LibraryLogger.logMessage("run() LEAVE", LibraryLoggerType.DEBUG,
	// LibraryClientRpcWorker.class);
	// }

	@Override
	public void bookBorrowed(int bookId, int quantity, boolean isCurrentUser) throws LibraryException {
		LibraryLogger.logMessage("bookBorrowed() ENTER", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		IBookBorrowDTO bookBorrowDTO = new BookBorrowDTO(bookId, quantity, isCurrentUser);
		Response response = new Response.Builder().type(ResponseType.BORROW_BOOK).data(bookBorrowDTO).build();
		try {
			sendJsonResponse(response);
		} catch (IOException e) {
			throw new LibraryException("Sending error: " + e);
		}
		LibraryLogger.logMessage("bookBorrowed() LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
	}

	@Override
	public void bookReturned(int bookId, String author, String title, boolean isCurrentUser) throws LibraryException {
		LibraryLogger.logMessage("bookReturned() LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		IBookReturnDTO bookReturnedDTO = new BookReturnDTO(bookId, author, title, isCurrentUser);
		Response response = new Response.Builder().type(ResponseType.RETURN_BOOK).data(bookReturnedDTO).build();
		try {
			sendJsonResponse(response);
		} catch (IOException e) {
			throw new LibraryException("Sending error: " + e);
		}
		LibraryLogger.logMessage("bookReturned() LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
	}

	@Deprecated
	@Override
	public void bookUpdated(int bookId, int newQuantity) throws LibraryException {
		LibraryLogger.logMessage("bookUpdated() ENTER", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		IBookQuantityDTO bookQuantityDTO = new BookQuantityDTO(bookId, newQuantity);
		Response response = new Response.Builder().type(ResponseType.BORROW_BOOK).data(bookQuantityDTO).build();
		try {
			sendJsonResponse(response);
		} catch (IOException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryClientRpcWorker.class);
			throw new LibraryException("Error occurs when trying to send response. Contact your administrator.");
		}
		LibraryLogger.logMessage("bookUpdated() LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
	}

	@Deprecated
	@Override
	public void bookReturned(int bookId, String author, String title) throws LibraryException {
		LibraryLogger.logMessage("bookReturned() ENTER", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		IBookDTO bookDTO = new BookDTO(bookId, author, title);
		Response response = new Response.Builder().type(ResponseType.RETURN_BOOK).data(bookDTO).build();
		try {
			sendJsonResponse(response);
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
				return new Response.Builder().type(ResponseType.LOGIN_SUCCESS).data(user).build();
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
				return new Response.Builder().type(ResponseType.LOGOUT_SUCCESS).build();
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

	private void sendJsonResponse(Response response) throws IOException {
		LibraryLogger.logMessage("sendResponse() ENTER", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
		LibraryLogger.logMessage("Sending response...", LibraryLoggerType.INFO, LibraryClientRpcWorker.class);
		Gson gson = new Gson();
		String json = gson.toJson(response);
		jsonOutput.println(json);
		jsonOutput.flush();
		LibraryLogger.logMessage("Response has been sent.\n" + response, LibraryLoggerType.INFO,
				LibraryClientRpcWorker.class);
		LibraryLogger.logMessage("sendResponse() LEAVE", LibraryLoggerType.DEBUG, LibraryClientRpcWorker.class);
	}

	@SuppressWarnings("unused")
	@Deprecated
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
