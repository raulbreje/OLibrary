package com.breje.network.json;

import java.lang.reflect.Type;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.model.Book;
import com.breje.model.impl.BookImpl;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class BookDeserializer implements JsonDeserializer<Book> {

	@Override
	public Book deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		LibraryLogger.logMessage("deserialize() ENTER", LibraryLoggerType.DEBUG, BookDeserializer.class);
		JsonObject jsonObject = arg0.getAsJsonObject();
		int bookId = jsonObject.get("bookId").getAsInt();
		String author = jsonObject.get("author").getAsString();
		String title = jsonObject.get("title").getAsString();
		int available = jsonObject.get("available").getAsInt();
		LibraryLogger.logMessage("deserialize() LEAVE", LibraryLoggerType.DEBUG, BookDeserializer.class);
		return new BookImpl(bookId, author, title, available);
	}
}
