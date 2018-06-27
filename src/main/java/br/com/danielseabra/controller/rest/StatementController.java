package br.com.danielseabra.controller.rest;

import static br.com.danielseabra.util.ApplicationUtils.getBankProcessorName;
import static br.com.danielseabra.util.ApplicationUtils.getFileExtensionFromMultipartFile;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.danielseabra.component.BankProcessor;
import br.com.danielseabra.component.validator.RequestPayloadValidator;
import br.com.danielseabra.service.BankProcessorProviderService;
import br.com.danielseabra.statement.Statement;

@RestController
@RequestMapping("/statement")
public class StatementController {

	@Autowired
	private RequestPayloadValidator requestPayloadValidator;

	@Autowired
	private BankProcessorProviderService bankProcessorProviderService;

	@PostMapping(consumes = { MULTIPART_FORM_DATA_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Collection<Statement>> saveStatements(final @RequestParam(value = "file") MultipartFile file,
			final @RequestParam("bankName") String bankName,
			final @RequestParam("modelName") String modelName) throws Exception {
		this.requestPayloadValidator.validateUploadRequestPayload(file, bankName, modelName);
		final String fileExtensionName = getFileExtensionFromMultipartFile(file);
		final BankProcessor bankProcessor = this.bankProcessorProviderService.getBankProcessor(getBankProcessorName(bankName, fileExtensionName, modelName));
		return ResponseEntity.ok(bankProcessor.process(file));
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<JsonNode> testRequest() {
		final ObjectNode replyNode = new ObjectNode(JsonNodeFactory.instance);
		replyNode.put("status", "Working Fine!");
		return ResponseEntity.ok(replyNode);
	}

}
