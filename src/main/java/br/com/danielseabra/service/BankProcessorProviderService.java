package br.com.danielseabra.service;

import br.com.danielseabra.component.BankProcessor;
import br.com.danielseabra.exception.InvalidBankProcessorNameValidationException;

public interface BankProcessorProviderService {

	BankProcessor getBankProcessor(String bankProcessorName) throws InvalidBankProcessorNameValidationException;

}
