package br.com.danielseabra.common.payload.exception;

import br.com.danielseabra.common.category.ExceptionType;
import br.com.danielseabra.common.category.MessageType;
import br.com.danielseabra.common.payload.ResponsePayload;

public interface ExceptionResponsePayload<T> extends ResponsePayload {

	@Override
	default MessageType getMessageType() {
		return MessageType.EXCEPTION;
	}

	ExceptionType getExceptionType();

	String getMessage();

	T getDetails();

}
