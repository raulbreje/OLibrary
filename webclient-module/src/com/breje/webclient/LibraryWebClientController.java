//package com.breje.webclient;
//
//import javax.swing.table.TableModel;
//
//import com.breje.exceptions.LibraryException;
//import com.breje.model.User;
//import com.breje.services.ILibraryClient;
//import com.breje.services.ILibraryServer;
//
//public class LibraryWebClientController implements ILibraryClient {
//
//	// private BooksTableModel availableBooksTableModel;
//	// private BooksTableModel yourBooksTableModel;
//	private ILibraryServer libraryServer;
//	private User user;
//
//	public LibraryWebClientController(ILibraryServer libraryServer) {
//		this.libraryServer = libraryServer;
//		// availableBooksTableModel = new BooksTableModel(true);
//		// yourBooksTableModel = new BooksTableModel(false);
//	}
//
//	public void login(String userName, String password) throws LibraryException {
//		User user = libraryServer.login(userName, password, this);
//		this.user = user;
//		// loadAvailableBooks();
//		// List<Book> userBooks =
//		// libraryServer.getUserBooks(this.user.getUserId());
//		// yourBooksTableModel.setBooks(userBooks);
//	}
//
//	public void logout() {
//		try {
//			libraryServer.logout(user.getUserId(), this);
//		} catch (LibraryException e) {
//			System.out.println("Logout error " + e);
//		}
//	}
//
//	public TableModel getAvailableBooksTableModel() {
//		return null;
//	}
//
//	public void loadAvailableBooks() throws LibraryException {
//		// List<Book> availableBooks = libraryServer.getAvailableBooks();
//		// availableBooksTableModel.setBooks(availableBooks);
//	}
//
//	public BooksTableModel getYourBooksTableModel() {
//		return null;
//	}
//
//	public void borrowBook(int bookId) throws LibraryException {
//		libraryServer.borrowBook(user.getUserId(), bookId);
//	}
//
//	public void returnBook(int bookId) throws LibraryException {
//		libraryServer.returnBook(user.getUserId(), bookId);
//	}
//
//	public void searchBooks(String key) throws LibraryException {
//		// List<Book> foundBooks = libraryServer.searchBooks(key);
//		// availableBooksTableModel.setBooks(foundBooks);
//	}
//
//	@Override
//	public void bookBorrowed(int bookId, int quantity, boolean isCurrentUser) throws LibraryException {
//		// if (isCurrentUser) {
//		// Book borrowed = availableBooksTableModel.getBookById(bookId);
//		// yourBooksTableModel.addBook(borrowed);
//		// }
//		// if (quantity == 0) {
//		// availableBooksTableModel.removeBookById(bookId);
//		// } else {
//		// availableBooksTableModel.getBookById(bookId).setAvailable(quantity);
//		// availableBooksTableModel.fireTableDataChanged();
//		// }
//	}
//
//	@Override
//	public void bookReturned(int bookId, String author, String title, boolean isCurrentUser) throws LibraryException {
//		// if (isCurrentUser) {
//		// yourBooksTableModel.removeBookById(bookId);
//		// }
//		// Book returnedBook = availableBooksTableModel.getBookById(bookId);
//		// if (returnedBook == null) {
//		// returnedBook = new BookImpl(bookId, author, title, 1);
//		// availableBooksTableModel.addBook(returnedBook);
//		// } else {
//		// returnedBook.setAvailable(returnedBook.getAvailable() + 1);
//		// availableBooksTableModel.fireTableDataChanged();
//		// }
//	}
//
//	@Override
//	@Deprecated
//	public void bookUpdated(int bookId, int newQuantity) throws LibraryException {
//		// if (newQuantity == 0) {
//		// availableBooksTableModel.removeBookById(bookId);
//		// } else {
//		// availableBooksTableModel.getBookById(bookId).setAvailable(newQuantity);
//		// availableBooksTableModel.fireTableDataChanged();
//		// }
//	}
//
//	@Override
//	@Deprecated
//	public void bookReturned(int bookId, String author, String title) throws LibraryException {
//		// Book returnedBook = availableBooksTableModel.getBookById(bookId);
//		// if (returnedBook == null) {
//		// returnedBook = new BookImpl(bookId, author, title, 1);
//		// availableBooksTableModel.addBook(returnedBook);
//		// } else {
//		// returnedBook.setAvailable(returnedBook.getAvailable() + 1);
//		// availableBooksTableModel.fireTableDataChanged();
//		// }
//	}
//
//}
