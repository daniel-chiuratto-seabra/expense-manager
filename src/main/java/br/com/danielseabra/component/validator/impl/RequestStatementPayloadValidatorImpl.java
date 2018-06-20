package br.com.danielseabra.component.validator.impl;

import static br.com.danielseabra.common.ApplicationConstants.PARAMETER_SEPARATOR;
import static br.com.danielseabra.common.RequestPayloadParameters.BANK_NAME;
import static br.com.danielseabra.common.RequestPayloadParameters.FILE_EXTENSION_NAME;
import static br.com.danielseabra.common.RequestPayloadParameters.MODEL_NAME;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.danielseabra.component.validator.RequestPayloadValidator;
import br.com.danielseabra.exception.missingparameter.impl.MissingParameterException;

@Component
public class RequestStatementPayloadValidatorImpl implements RequestPayloadValidator {

	@Override
	public void validateSaveStatementsPayload(final JsonNode node) throws MissingParameterException {
		if (isFieldNotAvailable(node, BANK_NAME.getParameterName()) ||
				isFieldNotAvailable(node, FILE_EXTENSION_NAME.getParameterName()) ||
				isFieldNotAvailable(node, MODEL_NAME.getParameterName())) {
			throw new MissingParameterException("There are missing parameter(s) for Save Statement Request Payload", buildParameterMessage(node).split(PARAMETER_SEPARATOR));
		}
	}
	
	private static boolean isFieldNotAvailable(final JsonNode node, final String parameterName) {
		return !node.hasNonNull(parameterName);
	}

	private static String buildParameterMessage(final JsonNode node) {
		final StringBuilder sb = new StringBuilder();
		if (isFieldNotAvailable(node, BANK_NAME.getParameterName()))
			concatenateFieldOnMessage(sb, BANK_NAME.getParameterName());
		if (isFieldNotAvailable(node, FILE_EXTENSION_NAME.getParameterName()))
			concatenateFieldOnMessage(sb, FILE_EXTENSION_NAME.getParameterName());
		if (isFieldNotAvailable(node, MODEL_NAME.getParameterName()))
			concatenateFieldOnMessage(sb, MODEL_NAME.getParameterName());
		return sb.toString();
	}

	private static void concatenateFieldOnMessage(final StringBuilder sb, final String parameterName) {
		if (sb.length() > 0)
			sb.append(PARAMETER_SEPARATOR);
		sb.append(parameterName);
	}

}
