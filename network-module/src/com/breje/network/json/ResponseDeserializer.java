package com.breje.network.json;

import java.lang.reflect.Type;

import com.breje.network.protocols.rpc.Response;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ResponseDeserializer implements JsonDeserializer<Response>{

	@Override
	public Response deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		return null;
	}

}
