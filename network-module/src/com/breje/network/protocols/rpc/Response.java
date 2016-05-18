package com.breje.network.protocols.rpc;

import java.io.Serializable;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;

public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1504898675199757436L;
	private ResponseType type;
	private Object data;

	private Response() {
	}

	public ResponseType type() {
		LibraryLogger.logMessage("constructor ENTER", LibraryLoggerType.DEBUG, Response.class);
		LibraryLogger.logMessage("constructor LEAVE", LibraryLoggerType.DEBUG, Response.class);
		return type;
	}

	public Object data() {
		LibraryLogger.logMessage("data() ENTER", LibraryLoggerType.DEBUG, Response.class);
		LibraryLogger.logMessage("data() LEAVE", LibraryLoggerType.DEBUG, Response.class);
		return data;
	}

	private void type(ResponseType type) {
		LibraryLogger.logMessage("type() ENTER", LibraryLoggerType.DEBUG, Response.class);
		this.type = type;
		LibraryLogger.logMessage("type() LEAVE", LibraryLoggerType.DEBUG, Response.class);
	}

	private void data(Object data) {
		LibraryLogger.logMessage("data() ENTER", LibraryLoggerType.DEBUG, Response.class);
		this.data = data;
		LibraryLogger.logMessage("data() LEAVE", LibraryLoggerType.DEBUG, Response.class);
	}

	@Override
	public String toString() {
		LibraryLogger.logMessage("toString() ENTER", LibraryLoggerType.DEBUG, Response.class);
		LibraryLogger.logMessage("toString() LEAVE", LibraryLoggerType.DEBUG, Response.class);
		return "Response{" + "type='" + type + "\'" + ", data='" + data + "\'" + "}";
	}

	public static class Builder {
		private Response response = new Response();

		public Builder type(ResponseType type) {
			LibraryLogger.logMessage("type() ENTER", LibraryLoggerType.DEBUG, Builder.class);
			response.type(type);
			LibraryLogger.logMessage("type() LEAVE", LibraryLoggerType.DEBUG, Builder.class);
			return this;
		}

		public Builder data(Object data) {
			LibraryLogger.logMessage("data() ENTER", LibraryLoggerType.DEBUG, Builder.class);
			response.data(data);
			LibraryLogger.logMessage("data() LEAVE", LibraryLoggerType.DEBUG, Builder.class);
			return this;
		}

		public Response build() {
			LibraryLogger.logMessage("build() ENTER", LibraryLoggerType.DEBUG, Builder.class);
			LibraryLogger.logMessage("build() LEAVE", LibraryLoggerType.DEBUG, Builder.class);
			return response;
		}
	}
}
