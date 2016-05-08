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
import com.breje.model.Book;
import com.breje.model.User;
import com.breje.network.dto.BookDTO;
import com.breje.network.dto.BookQuantityDTO;
import com.breje.network.dto.UserBookDTO;
import com.breje.network.dto.UserDTO;
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
		UserDTO userDTO = new UserDTO(userName, password);
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
		LibraryLogger.logMessage("login LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
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
	public List<Book> getAvailableBooks() throws LibraryException {
		LibraryLogger.logMessage("getAvailableBooks() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		Request request = new Request.Builder().type(RequestType.GET_AVAILABLE_BOOKS).build();
		sendRequest(request);
		Response response = readResponse();
		if (response.type() == ResponseType.ERROR) {
			String errorMessage = response.data().toString();
			closeConnection();
			throw new LibraryException(errorMessage);
		}
		List<Book> allBooks = (List<Book>) response.data();
		LibraryLogger.logMessage("getAvailableBooks() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		return allBooks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getUserBooks(int userId) throws LibraryException {
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
		return (List<Book>) response.data();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> searchBooks(String key) throws LibraryException {
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
		return (List<Book>) response.data();
	}

	@Override
	public void borrowBook(int userId, int bookId) throws LibraryException {
		LibraryLogger.logMessage("borrowBook() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		UserBookDTO userBookDTO = new UserBookDTO(userId, bookId);
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
		UserBookDTO userBookDTO = new UserBookDTO(userId, bookId);
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
		} catch (IOException e) {
			LibraryLogger.logMessage("Closing connection error." + e.getStackTrace(), LibraryLoggerType.ERROR,
					LibraryServerRpcProxy.class);
		}
		LibraryLogger.logMessage("closeConnection() LEAVE", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
	}

	private void sendRequest(Request request) throws LibraryException {
		LibraryLogger.logMessage("sendRequest() ENTER", LibraryLoggerType.DEBUG, LibraryServerRpcProxy.class);
		try {
			output.writeObject(request);
			output.flush();
		} catch (IOException e) {
			LibraryLogger.logMessage("Sending object error.\n" + e.getStackTrace(), LibraryLoggerType.ERROR,
					LibraryServerRpcProxy.class);
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
			LibraryLogger.logMessage("Interferation.\n" + e.getStackTrace(), LibraryLoggerType.ERROR,
					LibraryServerRpcProxy.class);
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
			startReader();
		} catch (IOException e) {
			LibraryLogger.logMessage("Initialization of connection failed\n" + e.getStackTrace(),
					LibraryLoggerType.ERROR, LibraryServerRpcProxy.class);
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
				BookQuantityDTO bookQuantityDTO = (BookQuantityDTO) response.data();
				client.bookUpdated(bookQuantityDTO.getBookId(), bookQuantityDTO.getNewQuantity());
			} catch (LibraryException exception) {

			}
		}
		if (response.type() == ResponseType.RETURN_BOOK) {
			try {
				BookDTO bookDTO = (BookDTO) response.data();
				client.bookReturned(bookDTO.getId(), bookDTO.getAuthor(), bookDTO.getTitle());
			} catch (LibraryException exception) {

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
					System.out.println("response received " + response);
					if (isUpdate((Response) response)) {
						handleUpdate((Response) response);
					} else {
						try {
							qresponses.put((Response) response);
						} catch (InterruptedException e) {
							LibraryLogger.logMessage("Interferation.\n" + e.getStackTrace(), LibraryLoggerType.ERROR,
									ReaderThread.class);
						}
					}
				} catch (IOException e) {
					LibraryLogger.logMessage("Error occurs.\n" + e.getStackTrace(), LibraryLoggerType.ERROR,
							ReaderThread.class);
				} catch (ClassNotFoundException e) {
					LibraryLogger.logMessage("Error occurs.\n" + e.getStackTrace(), LibraryLoggerType.ERROR,
							ReaderThread.class);
				}
			}
			LibraryLogger.logMessage("run() LEAVE", LibraryLoggerType.DEBUG, ReaderThread.class);
		}
	}
}
