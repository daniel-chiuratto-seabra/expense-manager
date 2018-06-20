package br.com.danielseabra.controller;

import static br.com.danielseabra.util.ApplicationUtils.getBankProcessorName;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.danielseabra.component.BankProcessor;
import br.com.danielseabra.component.validator.RequestPayloadValidator;
import br.com.danielseabra.exception.missingparameter.impl.MissingParameterException;
import br.com.danielseabra.service.BankProcessorProviderService;
import br.com.danielseabra.statement.Statement;

@RestController
@RequestMapping("/statement")
public class StatementController {

	@Autowired
	private RequestPayloadValidator requestPayloadValidator;

	@Autowired
	private BankProcessorProviderService bankProcessorProviderService;

	@ResponseBody
	@PostMapping(consumes = { MULTIPART_FORM_DATA_VALUE, APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Collection<Statement>> saveStatements(final MultipartFile[] file, final JsonNode node) throws MissingParameterException, IOException {
		this.requestPayloadValidator.validateSaveStatementsPayload(node);
		final BankProcessor bankProcessor = this.bankProcessorProviderService.getBankProcessor(getBankProcessorName(node));
		return ResponseEntity.ok(bankProcessor.process(file));
	}

}
