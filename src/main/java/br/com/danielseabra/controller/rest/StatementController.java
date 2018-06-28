package br.com.danielseabra.controller.rest;

import static br.com.danielseabra.component.util.ApplicationStaticUtils.getBankProcessorName;
import static br.com.danielseabra.component.util.ApplicationStaticUtils.getFileExtensionFromMultipartFile;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.danielseabra.component.BankProcessor;
import br.com.danielseabra.component.util.MessageSourceUtil;
import br.com.danielseabra.exception.RequestPayloadValidationException;
import br.com.danielseabra.formbean.FileUploadFormBean;
import br.com.danielseabra.service.BankProcessorProviderService;
import br.com.danielseabra.statement.Statement;

@RestController
@RequestMapping("/statement")
public class StatementController {

	@Autowired
	private MessageSourceUtil serializationUtil;

	@Autowired
	private BankProcessorProviderService bankProcessorProviderService;

	@PostMapping(consumes = { MULTIPART_FORM_DATA_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<Collection<Statement>> saveStatements(final @Valid @ModelAttribute("fileUploadFormBean") FileUploadFormBean bean,
			final BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors())
			throw new RequestPayloadValidationException(this.serializationUtil, bindingResult);
		final String fileExtensionName = getFileExtensionFromMultipartFile(bean.getFile());
		final BankProcessor bankProcessor = this.bankProcessorProviderService
				.getBankProcessor(getBankProcessorName(bean.getBankName(), fileExtensionName, bean.getModelName()));
		return ResponseEntity.ok(bankProcessor.process(bean.getFile()));
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<JsonNode> testRequest() {
		final ObjectNode replyNode = new ObjectNode(JsonNodeFactory.instance);
		replyNode.put("status", "Working Fine!");
		return ResponseEntity.ok(replyNode);
	}

}
