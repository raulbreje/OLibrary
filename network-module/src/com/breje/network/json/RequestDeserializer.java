package com.breje.network.json;

import java.lang.reflect.Type;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.network.dto.impl.UserBookDTO;
import com.breje.network.dto.impl.UserDTO;
import com.breje.network.protocols.rpc.Request;
import com.breje.network.protocols.rpc.RequestType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class RequestDeserializer implements JsonDeserializer<Request> {

	@Override
	public Request deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
		LibraryLogger.logMessage("deserialize() ENTER", LibraryLoggerType.DEBUG, RequestDeserializer.class);
		Request request = null;
		JsonObject jsonObject = arg0.getAsJsonObject();
		RequestType requestType = RequestType.valueOf(jsonObject.get("type").getAsString());
		switch (requestType) {
		case LOGIN:
			request = new Request.Builder().type(RequestType.LOGIN)
					.data(arg2.deserialize(jsonObject.get("data"), UserDTO.class)).build();
			break;
		case LOGOUT:
			request = new Request.Builder().type(RequestType.LOGOUT).data(jsonObject.get("data").getAsInt()).build();
			break;
		case GET_AVAILABLE_BOOKS:
			request = new Request.Builder().type(RequestType.GET_AVAILABLE_BOOKS).build();
			break;
		case GET_USER_BOOKS:
			request = new Request.Builder().type(RequestType.GET_USER_BOOKS).data(jsonObject.get("data").getAsInt())
					.build();
			break;
		case SEARCH_BOOKS:
			request = new Request.Builder().type(RequestType.GET_USER_BOOKS).data(jsonObject.get("data").getAsString())
					.build();
			break;
		case BORROW_BOOK:
			request = new Request.Builder().type(RequestType.BORROW_BOOK)
					.data(arg2.deserialize(jsonObject.get("data"), UserBookDTO.class)).build();
			break;
		case RETURN_BOOK:
			request = new Request.Builder().type(RequestType.RETURN_BOOK)
					.data(arg2.deserialize(jsonObject.get("data"), UserBookDTO.class)).build();
			break;
		}
		LibraryLogger.logMessage("deserialize() LEAVE", LibraryLoggerType.DEBUG, RequestDeserializer.class);
		return request;
	}

}
