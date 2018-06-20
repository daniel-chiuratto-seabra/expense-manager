package br.com.danielseabra.util;

import static br.com.danielseabra.common.RequestPayloadParameters.BANK_NAME;
import static br.com.danielseabra.common.RequestPayloadParameters.FILE_EXTENSION_NAME;
import static br.com.danielseabra.common.RequestPayloadParameters.MODEL_NAME;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.danielseabra.exception.missingparameter.impl.MissingParameterException;

public class ApplicationUtils {

	public static String getBankProcessorName(final JsonNode node) throws MissingParameterException {
		return new StringBuilder(node.get(BANK_NAME.getParameterName()).asText().toLowerCase())
				.append(node.get(FILE_EXTENSION_NAME.getParameterName()).asText().toUpperCase())
				.append(getCamelCasedString(node.get(MODEL_NAME.getParameterName()).asText()))
				.toString();
	}

	public static String getCamelCasedString(String value) {
		value = value.toLowerCase();
		value = value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
		return value;
	}
}
