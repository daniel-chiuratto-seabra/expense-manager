package br.com.danielseabra.exception.inner.impl;

import br.com.danielseabra.exception.inner.InnerExceptionContainer;

public class MissingParameterException extends Exception implements InnerExceptionContainer {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private final String[] missingParameters;

	public MissingParameterException(final String... missingParameters) {
		this.missingParameters = missingParameters;
	}

	@Override
	public String[] getDetail() {
		return this.missingParameters;
	}

}
