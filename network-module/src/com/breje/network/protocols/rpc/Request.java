package com.breje.network.protocols.rpc;

import java.io.Serializable;

import com.breje.common.logging.LibraryLogger;
import com.breje.common.logging.LibraryLoggerType;

public class Request implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5343106145295072452L;
	private RequestType type;
	private Object data;

	private Request() {
	}

	public RequestType type() {
		LibraryLogger.logMessage("type() ENTER", LibraryLoggerType.DEBUG, Request.class);
		LibraryLogger.logMessage("type() LEAVE", LibraryLoggerType.DEBUG, Request.class);
		return type;
	}

	public Object data() {
		LibraryLogger.logMessage("data() ENTER", LibraryLoggerType.DEBUG, Request.class);
		LibraryLogger.logMessage("data() LEAVE", LibraryLoggerType.DEBUG, Request.class);
		return data;
	}

	@Override
	public String toString() {
		LibraryLogger.logMessage("toString() ENTER", LibraryLoggerType.DEBUG, Request.class);
		LibraryLogger.logMessage("toString() LEAVE", LibraryLoggerType.DEBUG, Request.class);
		return "Request{" + "type='" + type + "\'" + ", data='" + data + "\'" + "}";
	}

	public static class Builder {
		private Request request = new Request();

		public Builder type(RequestType type) {
			LibraryLogger.logMessage("build() ENTER", LibraryLoggerType.DEBUG, Builder.class);
			request.type(type);
			LibraryLogger.logMessage("build() LEAVE", LibraryLoggerType.DEBUG, Builder.class);
			return this;
		}

		public Builder data(Object data) {
			LibraryLogger.logMessage("data() ENTER", LibraryLoggerType.DEBUG, Builder.class);
			request.data(data);
			LibraryLogger.logMessage("data() LEAVE", LibraryLoggerType.DEBUG, Builder.class);
			return this;
		}

		public Request build() {
			LibraryLogger.logMessage("build() ENTER", LibraryLoggerType.DEBUG, Builder.class);
			LibraryLogger.logMessage("build() LEAVE", LibraryLoggerType.DEBUG, Builder.class);
			return request;
		}
	}

	private void data(Object data) {
		LibraryLogger.logMessage("data() ENTER", LibraryLoggerType.DEBUG, Request.class);
		LibraryLogger.logMessage("date() LEAVE", LibraryLoggerType.DEBUG, Request.class);
		this.data = data;
	}

	private void type(RequestType type) {
		LibraryLogger.logMessage("type() ENTER", LibraryLoggerType.DEBUG, Request.class);
		LibraryLogger.logMessage("type() LEAVE", LibraryLoggerType.DEBUG, Request.class);
		this.type = type;
	}
}
