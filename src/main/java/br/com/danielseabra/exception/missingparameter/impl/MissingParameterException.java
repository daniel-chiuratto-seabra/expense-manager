package br.com.danielseabra.exception.missingparameter.impl;

import br.com.danielseabra.exception.ExceptionContainer;

public class MissingParameterException extends Exception implements ExceptionContainer {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final String[] parameters;

	public MissingParameterException(final String message, final String[] parameters) {
		super(message);
		this.parameters = parameters;
	}

	@Override
	public String[] getParameters() {
		return this.parameters;
	}

}
