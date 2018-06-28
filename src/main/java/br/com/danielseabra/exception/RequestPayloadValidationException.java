package br.com.danielseabra.exception;

import org.springframework.validation.BindingResult;

import br.com.danielseabra.component.util.ApplicationStaticUtils;
import br.com.danielseabra.component.util.MessageSourceUtil;

public class RequestPayloadValidationException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 710797253538445702L;

	public RequestPayloadValidationException(final MessageSourceUtil serializationUtil, final BindingResult bindingResult) {
		super(ApplicationStaticUtils.serializeBindingResultToMapString(bindingResult));
	}
}
