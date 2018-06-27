package br.com.danielseabra.exception.inner.impl;

import br.com.danielseabra.exception.inner.InnerExceptionContainer;

public class InvalidBankProcessorNameException extends Exception implements InnerExceptionContainer {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public InvalidBankProcessorNameException(final String bankProcessorName) {
		super(bankProcessorName);
	}

	@Override
	public String[] getDetail() {
		return new String[0];
	}

}
