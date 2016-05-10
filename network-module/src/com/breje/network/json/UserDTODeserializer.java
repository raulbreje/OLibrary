package com.breje.network.json;

import java.lang.reflect.Type;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.dto.IUserDTO;
import com.breje.network.dto.impl.UserDTO;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class UserDTODeserializer implements JsonDeserializer<IUserDTO> {

	@Override
	public IUserDTO deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		LibraryLogger.logMessage("deserialize() ENTER", LibraryLoggerType.DEBUG, UserDTODeserializer.class);
		JsonObject jsonObject = arg0.getAsJsonObject();
		String userName = jsonObject.get("userName").getAsString();
		String password = jsonObject.get("password").getAsString();
		LibraryLogger.logMessage("deserialize() ENTER", LibraryLoggerType.DEBUG, UserDTODeserializer.class);
		return new UserDTO(userName, password);
	}

}
