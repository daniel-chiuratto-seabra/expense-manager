package br.com.danielseabra.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielseabra.component.BankProcessor;
import br.com.danielseabra.service.BankProcessorProviderService;

@Service
public class BankProcessorProviderServiceImpl implements BankProcessorProviderService {

	@Autowired
	private Map<String, BankProcessor> processors;

	@Override
	public BankProcessor getBankProcessor(final String bankProcessorName) {
		return this.processors.get(bankProcessorName);
	}

}
