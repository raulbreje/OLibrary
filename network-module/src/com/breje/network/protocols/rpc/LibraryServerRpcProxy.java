package com.breje.network.protocols.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.exceptions.LibraryException;
import com.breje.model.User;
import com.breje.model.impl.BookImpl;
import com.breje.network.dto.IBookDTO;
import com.breje.network.dto.IBookQuantityDTO;
import com.breje.network.dto.IUserBookDTO;
import com.breje.network.dto.IUserDTO;
import com.breje.network.dto.impl.UserBookDTO;
import com.breje.network.dto.impl.UserDTO;
import com.breje.services.ILibraryClient;
import com.breje.services.ILibraryServer;

public class LibraryServerRpcProxy implements ILibraryServer {

	private String host;
	private int port;

	private ILibraryClient client;

	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket connection;

	private BlockingQueue<Response> qresponses;
	private volatile boolean finished;

	public LibraryServerRpcProxy(String host, int port) {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		this.host = host;
		this.port = port;
		qresponses = new LinkedBlockingQueue<>();
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
	}

	public User login(String userName, String password, ILibraryClient client) throws LibraryException {
		LibraryLogger.logMessage("login() ENTER", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		initializeConnection();
		IUserDTO userDTO = new UserDTO(userName, password);
		Request request = new Request.Builder().type(RequestType.LOGIN).data(userDTO).build();
		sendRequest(request);
		Response response = readResponse();
		if (response.type() == ResponseType.ERROR) {
			String errorMessage = response.data().toString();
			closeConnection();
			throw new LibraryException(errorMessage);
		}
		User user = (User) response.data();
		if (user != null && response.type() == ResponseType.OK) {
			this.client = client;
		}
		LibraryLogger.logMessage("login() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		return user;
	}

	public void logout(int userId, ILibraryClient client) throws LibraryException {
		LibraryLogger.logMessage("logout() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		Request request = new Request.Builder().type(RequestType.LOGOUT).data(userId).build();
		sendRequest(request);
		Response response = readResponse();
		closeConnection();
		if (response.type() == ResponseType.ERROR) {
			String errorMessage = response.data().toString();
			throw new LibraryException(errorMessage);
		}
		LibraryLogger.logMessage("logout() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookImpl> getAvailableBooks() throws LibraryException {
		LibraryLogger.logMessage("getAvailableBooks() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		Request request = new Request.Builder().type(RequestType.GET_AVAILABLE_BOOKS).build();
		sendRequest(request);
		Response response = readResponse();
		if (response.type() == ResponseType.ERROR) {
			String errorMessage = response.data().toString();
			closeConnection();
			throw new LibraryException(errorMessage);
		}
		List<BookImpl> allBooks = (List<BookImpl>) response.data();
		LibraryLogger.logMessage("getAvailableBooks() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		return allBooks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookImpl> getUserBooks(int userId) throws LibraryException {
		LibraryLogger.logMessage("getUserBooks() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		Request request = new Request.Builder().type(RequestType.GET_USER_BOOKS).data(userId).build();
		sendRequest(request);
		Response response = readResponse();
		if (response.type() == ResponseType.ERROR) {
			String errorMessage = response.data().toString();
			closeConnection();
			throw new LibraryException(errorMessage);
		}
		LibraryLogger.logMessage("getUserBooks() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		return (List<BookImpl>) response.data();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookImpl> searchBooks(String key) throws LibraryException {
		LibraryLogger.logMessage("searchBooks() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		Request request = new Request.Builder().type(RequestType.SEARCH_BOOKS).data(key).build();
		sendRequest(request);
		Response response = readResponse();
		if (response.type() == ResponseType.ERROR) {
			String errorMessage = response.data().toString();
			closeConnection();
			throw new LibraryException(errorMessage);
		}
		LibraryLogger.logMessage("searchBooks() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		return (List<BookImpl>) response.data();
	}

	@Override
	public void borrowBook(int userId, int bookId) throws LibraryException {
		LibraryLogger.logMessage("borrowBook() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		IUserBookDTO userBookDTO = new UserBookDTO(userId, bookId);
		Request request = new Request.Builder().type(RequestType.BORROW_BOOK).data(userBookDTO).build();
		sendRequest(request);
		Response response = readResponse();
		if (response.type() == ResponseType.ERROR) {
			String errorMessage = response.data().toString();
			throw new LibraryException(errorMessage);
		}
		LibraryLogger.logMessage("borrowBook() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
	}

	@Override
	public void returnBook(int userId, int bookId) throws LibraryException {
		LibraryLogger.logMessage("returnBook() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		IUserBookDTO userBookDTO = new UserBookDTO(userId, bookId);
		Request request = new Request.Builder().type(RequestType.RETURN_BOOK).data(userBookDTO).build();
		sendRequest(request);
		Response response = readResponse();
		if (response.type() == ResponseType.ERROR) {
			String errorMessage = response.data().toString();
			throw new LibraryException(errorMessage);
		}
		LibraryLogger.logMessage("returnBook() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
	}

	private void closeConnection() {
		LibraryLogger.logMessage("closeConnection() ENTER", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		finished = true;
		try {
			input.close();
			output.close();
			connection.close();
			client = null;
			LibraryLogger.logMessage("Connection has been closed with success.", LibraryLoggerType.INFO,
					LibraryServerRpcProxy.class);
		} catch (IOException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryServerRpcProxy.class);
		}
		LibraryLogger.logMessage("closeConnection() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
	}

	private void sendRequest(Request request) throws LibraryException {
		LibraryLogger.logMessage("sendRequest() ENTER", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		try {
			output.writeObject(request);
			output.flush();
		} catch (IOException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryServerRpcProxy.class);
			throw new LibraryException("Error when try to send the request. Contact your administator.");
		}
		LibraryLogger.logMessage("sendRequest() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
	}

	private Response readResponse() throws LibraryException {
		LibraryLogger.logMessage("readResponse() ENTER", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		Response response = null;
		try {
			response = qresponses.take();
		} catch (InterruptedException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryServerRpcProxy.class);
			throw new LibraryException("Error occurs when try to read the response. Contact your administrator.");
		}
		LibraryLogger.logMessage("readResponse() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		return response;
	}

	private void initializeConnection() throws LibraryException {
		LibraryLogger.logMessage("initializeConnection() ENTER", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		try {
			connection = new Socket(host, port);
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();
			input = new ObjectInputStream(connection.getInputStream());
			finished = false;
			LibraryLogger.logMessage("Connection has been initialized with success.", LibraryLoggerType.INFO,
					LibraryServerRpcProxy.class);
			startReader();
		} catch (IOException e) {
			LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryServerRpcProxy.class);
			throw new LibraryException(
					"Cannot estabilish the connection. Try again and if the error occurs repeateadly contact your administrator.");
		}
		LibraryLogger.logMessage("initializeConnection() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
	}

	private void startReader() {
		LibraryLogger.logMessage("startReader() ENTER", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		Thread tw = new Thread(new ReaderThread());
		tw.start();
		LibraryLogger.logMessage("startReader() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
	}

	private void handleUpdate(Response response) {
		LibraryLogger.logMessage("handleUpdate() ENTER", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		if (response.type() == ResponseType.BORROW_BOOK) {
			try {
				IBookQuantityDTO bookQuantityDTO = (IBookQuantityDTO) response.data();
				client.bookUpdated(bookQuantityDTO.getBookId(), bookQuantityDTO.getNewQuantity());
			} catch (LibraryException e) {
				LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryServerRpcProxy.class);
			}
		}
		if (response.type() == ResponseType.RETURN_BOOK) {
			try {
				IBookDTO bookDTO = (IBookDTO) response.data();
				client.bookReturned(bookDTO.getId(), bookDTO.getAuthor(), bookDTO.getTitle());
			} catch (LibraryException e) {
				LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, LibraryServerRpcProxy.class);
			}
		}
		LibraryLogger.logMessage("handleUpdate() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
	}

	private boolean isUpdate(Response response) {
		LibraryLogger.logMessage("isUpdate() ENTER", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		LibraryLogger.logMessage("isUpdate() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		return response.type() == ResponseType.BORROW_BOOK || response.type() == ResponseType.RETURN_BOOK;
	}

	private class ReaderThread implements Runnable {
		public void run() {
			LibraryLogger.logMessage("run() ENTER", LibraryLoggerType.DEBUG, ReaderThread.class);
			while (!finished) {
				try {
					Object response = input.readObject();
					LibraryLogger.logMessage("The server received a response:\n" + response, LibraryLoggerType.INFO,
							ReaderThread.class);
					if (isUpdate((Response) response)) {
						handleUpdate((Response) response);
					} else {
						try {
							qresponses.put((Response) response);
						} catch (InterruptedException e) {
							LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, ReaderThread.class);
						}
					}
				} catch (IOException e) {
					LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, ReaderThread.class);
				} catch (ClassNotFoundException e) {
					LibraryLogger.logMessage(e, LibraryLoggerType.ERROR, ReaderThread.class);
				}
			}
			LibraryLogger.logMessage("run() LEAVE", LibraryLoggerType.DEBUG, ReaderThread.class);
		}
	}
}
