package br.com.danielseabra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.danielseabra.exception.ExceptionContainer;
import br.com.danielseabra.exception.missingparameter.impl.MissingParameterException;
import br.com.danielseabra.exception.missingparameter.impl.MissingParameterResponsePayload;

@ControllerAdvice
public class ExceptionHandlerControllerImpl {

	@ExceptionHandler(MissingParameterException.class)
	public ResponseEntity<? extends ExceptionContainer> errorHandler(final ExceptionContainer exception) {
		return ResponseEntity.badRequest().body(new MissingParameterResponsePayload(exception));
	}

}
