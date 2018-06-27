package br.com.danielseabra.service;

import br.com.danielseabra.component.BankProcessor;
import br.com.danielseabra.exception.inner.impl.InvalidBankProcessorNameException;

public interface BankProcessorProviderService {

	BankProcessor getBankProcessor(String bankProcessorName) throws InvalidBankProcessorNameException;

}
