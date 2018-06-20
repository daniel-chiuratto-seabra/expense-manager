package br.com.danielseabra.component.validator;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.danielseabra.exception.missingparameter.impl.MissingParameterException;

public interface RequestPayloadValidator {

	void validateSaveStatementsPayload(JsonNode node) throws MissingParameterException;

}
