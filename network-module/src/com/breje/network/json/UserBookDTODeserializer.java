package com.breje.network.json;

import java.lang.reflect.Type;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.dto.IUserBookDTO;
import com.breje.network.dto.impl.UserBookDTO;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class UserBookDTODeserializer implements JsonDeserializer<UserBookDTO> {

	@Override
	public UserBookDTO deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		LibraryLogger.logMessage("deserialize() ENTER", LibraryLoggerType.DEBUG, UserBookDTODeserializer.class);
		JsonObject jsonObject = arg0.getAsJsonObject();
		int userId = Integer.parseInt(jsonObject.get("userId").getAsString());
		int bookId = Integer.parseInt(jsonObject.get("bookId").getAsString());
		LibraryLogger.logMessage("deserialize() LEAVE", LibraryLoggerType.DEBUG, UserBookDTODeserializer.class);
		return new UserBookDTO(userId, bookId);
	}

}
