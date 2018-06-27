package br.com.danielseabra.exception.impl;

import br.com.danielseabra.common.MessageType;
import br.com.danielseabra.exception.ResponseExceptionContainer;

public class ResponseExceptionContainerImpl implements ResponseExceptionContainer {

	private final MessageType messageType;
	private final String message;
	private final String[] detail;

	public ResponseExceptionContainerImpl(final MessageType messageType, final String message, final String... detail) {
		this.messageType = messageType;
		this.message = message;
		this.detail = detail;
	}

	public MessageType getMessageType() {
		return this.messageType;
	}

	public String getMessage() {
		return this.message;
	}

	public String[] getDetail() {
		return this.detail;
	}
}
