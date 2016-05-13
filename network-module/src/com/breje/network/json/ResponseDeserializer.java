package com.breje.network.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;
import com.breje.model.impl.BookImpl;
import com.breje.model.impl.UserImpl;
import com.breje.network.dto.impl.BookBorrowDTO;
import com.breje.network.dto.impl.BookReturnDTO;
import com.breje.network.protocols.rpc.Response;
import com.breje.network.protocols.rpc.ResponseType;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ResponseDeserializer implements JsonDeserializer<Response> {

	@Override
	public Response deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		LibraryLogger.logMessage("deserialize() ENTER", LibraryLoggerType.DEBUG, ResponseDeserializer.class);
		Response response = null;
		JsonObject jsonObject = arg0.getAsJsonObject();
		ResponseType responseType = ResponseType.valueOf(jsonObject.get("type").getAsString());
		switch (responseType) {
		case LOGIN_SUCCESS:
			response = new Response.Builder().type(ResponseType.LOGIN_SUCCESS)
					.data(arg2.deserialize(jsonObject.get("data"), UserImpl.class)).build();
			break;
		case LOGOUT_SUCCESS:
			response = new Response.Builder().type(ResponseType.LOGOUT_SUCCESS).build();
			break;
		case GET_AVAILABLE_BOOKS:
			List<BookImpl> availableBooks = new ArrayList<>();
			jsonObject.get("data").getAsJsonArray()
					.forEach(c -> availableBooks.add(arg2.deserialize(c, BookImpl.class)));
			response = new Response.Builder().type(ResponseType.GET_AVAILABLE_BOOKS).data(availableBooks).build();
			break;
		case GET_USER_BOOKS:
			List<BookImpl> userBooks = new ArrayList<>();
			jsonObject.get("data").getAsJsonArray().forEach(c -> userBooks.add(arg2.deserialize(c, BookImpl.class)));
			response = new Response.Builder().type(ResponseType.GET_USER_BOOKS).data(userBooks).build();
			break;
		case SEARCH_BOOKS:
			List<BookImpl> foundBooks = new ArrayList<>();
			jsonObject.get("data").getAsJsonArray().forEach(c -> foundBooks.add(arg2.deserialize(c, BookImpl.class)));
			response = new Response.Builder().type(ResponseType.SEARCH_BOOKS).data(foundBooks).build();
			break;
		case OK:
			response = new Response.Builder().type(ResponseType.OK).build();
			break;
		case ERROR:
			response = new Response.Builder().type(ResponseType.ERROR).build();
			break;
		case BORROW_BOOK:
			BookBorrowDTO bookBorrowDTO = arg2.deserialize(jsonObject.get("data"), BookBorrowDTO.class);
			response = new Response.Builder().type(ResponseType.BORROW_BOOK).data(bookBorrowDTO).build();
			break;
		case RETURN_BOOK:
			BookReturnDTO bookReturnDTO = arg2.deserialize(jsonObject.get("data"), BookReturnDTO.class);
			response = new Response.Builder().type(ResponseType.RETURN_BOOK).data(bookReturnDTO).build();
			break;
		}
		LibraryLogger.logMessage("deserialize() LEAVE", LibraryLoggerType.DEBUG, ResponseDeserializer.class);
		return response;
	}

}
