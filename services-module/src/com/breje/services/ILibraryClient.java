package com.breje.services;

import com.breje.exceptions.LibraryException;

public interface ILibraryClient {
	@Deprecated
	void bookUpdated(int bookId, int newQuantity) throws LibraryException;

	@Deprecated
	void bookReturned(int bookId, String author, String title) throws LibraryException;

	void bookBorrowed(int bookId, int quantity, boolean isCurrentUser) throws LibraryException;

	void bookReturned(int bookId, String author, String title, boolean isCurrentUser) throws LibraryException;
}
