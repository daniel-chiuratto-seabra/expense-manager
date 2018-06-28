package br.com.danielseabra.exception;

public class InvalidBankProcessorNameValidationException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 6989110890895651395L;

	public InvalidBankProcessorNameValidationException(final String bankProcessorName) {
		super(bankProcessorName);
	}

}
