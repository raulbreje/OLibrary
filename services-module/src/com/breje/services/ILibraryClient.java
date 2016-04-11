package com.breje.services;

import com.breje.exceptions.LibraryException;

public interface ILibraryClient {
	void bookUpdated(int bookId, int newQuantity) throws LibraryException;

	void bookReturned(int bookId, String author, String title) throws LibraryException;
}
