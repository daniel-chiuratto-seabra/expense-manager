package br.com.danielseabra.service;

import br.com.danielseabra.component.BankProcessor;

public interface BankProcessorProviderService {

	BankProcessor getBankProcessor(String bankProcessorName);

}
