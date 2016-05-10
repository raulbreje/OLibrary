package com.breje.network.json;

import java.lang.reflect.Type;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.dto.IBookBorrowDTO;
import com.breje.network.dto.impl.BookBorrowDTO;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class BookBorrowDTODeserializer implements JsonDeserializer<IBookBorrowDTO> {

	@Override
	public IBookBorrowDTO deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		LibraryLogger.logMessage("deserialize() ENTER", LibraryLoggerType.DEBUG, BookBorrowDTODeserializer.class);
		JsonObject jsonObject = arg0.getAsJsonObject();
		int bookId = jsonObject.get("bookId").getAsInt();
		int quantity = jsonObject.get("quantity").getAsInt();
		boolean isCurrentUser = jsonObject.get("isCurrentUser").getAsBoolean();
		LibraryLogger.logMessage("deserialize() LEAVE", LibraryLoggerType.DEBUG, BookBorrowDTODeserializer.class);
		return new BookBorrowDTO(bookId, quantity, isCurrentUser);
	}

}
