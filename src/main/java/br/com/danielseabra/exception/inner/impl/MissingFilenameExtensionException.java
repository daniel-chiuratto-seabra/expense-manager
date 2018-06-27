package br.com.danielseabra.exception.inner.impl;

import br.com.danielseabra.exception.inner.InnerExceptionContainer;

public class MissingFilenameExtensionException extends Exception implements InnerExceptionContainer {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String[] getDetail() {
		return null;
	}

}
