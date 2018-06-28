package br.com.danielseabra.controller.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import br.com.danielseabra.common.category.ExceptionType;
import br.com.danielseabra.common.payload.exception.ExceptionResponsePayload;
import br.com.danielseabra.common.payload.exception.impl.ExceptionResponsePayloadImpl;
import br.com.danielseabra.component.util.ApplicationStaticUtils;
import br.com.danielseabra.component.util.MessageSourceUtil;
import br.com.danielseabra.exception.RequestPayloadValidationException;

@RestControllerAdvice
public class ExceptionHandlerControllerImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerControllerImpl.class);

	@Autowired
	private MessageSourceUtil messageSourceUtil;

	@ExceptionHandler(RequestPayloadValidationException.class)
	public ResponseEntity<ExceptionResponsePayload<Map<String, String>>> requestPayloadValidationExceptionHandler(
			final RequestPayloadValidationException exception) {
		final String exceptionMessage = this.messageSourceUtil.retrieveMessage("log.message.validationerror.message");
		if (LOGGER.isErrorEnabled()) {
			LOGGER.error(exceptionMessage);
		}
		return ResponseEntity.badRequest()
				.body(new ExceptionResponsePayloadImpl<>(ExceptionType.VALIDATION, exceptionMessage,
						ApplicationStaticUtils.deserializeMapStringIntoMap(exception.getMessage())));
	}

	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<ExceptionResponsePayload<String>> multipartExceptionHandler(final MultipartException exception) {
		final String exceptionMessage = this.messageSourceUtil.retrieveMessage("log.message.fileuploadprocesserror.message");
		if (LOGGER.isErrorEnabled()) {
			LOGGER.error(exceptionMessage, exception);
		}
		return ResponseEntity.badRequest()
				.body(new ExceptionResponsePayloadImpl<>(ExceptionType.FILE_UPLOAD, exceptionMessage, ApplicationStaticUtils.getStackTrace(exception)));
	}

}
