package com.breje.network.json;

import java.lang.reflect.Type;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.dto.IBookReturnDTO;
import com.breje.network.dto.impl.BookReturnDTO;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class BookReturnDTODeserializer implements JsonDeserializer<BookReturnDTO> {

	@Override
	public BookReturnDTO deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		LibraryLogger.logMessage("deserialize() ENTER", LibraryLoggerType.DEBUG, BookReturnDTODeserializer.class);
		JsonObject jsonObject = arg0.getAsJsonObject();
		int bookId = jsonObject.get("bookId").getAsInt();
		String author = jsonObject.get("author").getAsString();
		String title = jsonObject.get("title").getAsString();
		boolean byThisUser = jsonObject.get("isCurrentUser").getAsBoolean();
		LibraryLogger.logMessage("deserialize() LEAVE", LibraryLoggerType.DEBUG, BookReturnDTODeserializer.class);
		return new BookReturnDTO(bookId, author, title, byThisUser);
	}

}
