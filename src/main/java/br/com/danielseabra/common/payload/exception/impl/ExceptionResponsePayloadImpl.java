package br.com.danielseabra.common.payload.exception.impl;

import br.com.danielseabra.common.category.ExceptionType;
import br.com.danielseabra.common.payload.exception.ExceptionResponsePayload;

public class ExceptionResponsePayloadImpl<T> implements ExceptionResponsePayload<T> {

	private final ExceptionType exceptionType;
	private final String message;
	private final T details;

	public ExceptionResponsePayloadImpl(final ExceptionType exceptionType, final String message, final T details) {
		this.exceptionType = exceptionType;
		this.message = message;
		this.details = details;
	}

	@Override
	public ExceptionType getExceptionType() {
		return this.exceptionType;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public T getDetails() {
		return this.details;
	}

}
