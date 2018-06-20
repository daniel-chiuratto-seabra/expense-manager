package br.com.danielseabra.exception.missingparameter.impl;

import br.com.danielseabra.exception.ExceptionContainer;

public class MissingParameterResponsePayload implements ExceptionContainer {

	private final String message;
	private final String[] parameters;

	public MissingParameterResponsePayload(final ExceptionContainer exceptionSource) {
		this.message = exceptionSource.getMessage();
		this.parameters = exceptionSource.getParameters();
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public String[] getParameters() {
		return this.parameters;
	}

}
