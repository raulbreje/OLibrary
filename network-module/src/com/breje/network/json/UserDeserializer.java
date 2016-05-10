package com.breje.network.json;

import java.lang.reflect.Type;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.model.User;
import com.breje.model.impl.UserImpl;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class UserDeserializer implements JsonDeserializer<User> {

	@Override
	public User deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		LibraryLogger.logMessage("deserialize() ENTER", LibraryLoggerType.DEBUG, UserDeserializer.class);
		JsonObject jsonObject = arg0.getAsJsonObject();
		int userId = jsonObject.get("userId").getAsInt();
		String userName = jsonObject.get("userName").getAsString();
		String password = jsonObject.get("password").getAsString();
		String fullName = jsonObject.get("fullName").getAsString();
		LibraryLogger.logMessage("deserialize() LEAVE", LibraryLoggerType.DEBUG, UserDeserializer.class);
		return new UserImpl(userId, userName, password, fullName);
	}

}
