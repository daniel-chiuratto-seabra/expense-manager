package br.com.danielseabra.controller.rest;

import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import br.com.danielseabra.exception.ResponseExceptionContainer;
import br.com.danielseabra.exception.inner.impl.InvalidBankProcessorNameException;
import br.com.danielseabra.exception.inner.impl.MissingFilenameExtensionException;
import br.com.danielseabra.exception.inner.impl.MissingParameterException;
import br.com.danielseabra.transformer.ResponseExceptionContainerTransformer;

@RestControllerAdvice
public class ExceptionHandlerControllerImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerControllerImpl.class);

	@Value("${message.exception.handler.log}")
	private String exceptionHandlerLogMessage;

	@Autowired
	private ResponseExceptionContainerTransformer transformer;

	@ExceptionHandler(MissingParameterException.class)
	public ResponseEntity<ResponseExceptionContainer> missingParameterExceptionHandler(final MissingParameterException exception) {
		LOGGER.error(format(this.exceptionHandlerLogMessage, exception.getClass().getSimpleName()), exception);
		return ResponseEntity.badRequest().body(this.transformer.transform(exception, "There are missing parameter(s) on the request payload"));
	}

	@ExceptionHandler(MissingFilenameExtensionException.class)
	public ResponseEntity<ResponseExceptionContainer> missingExtensionOnFilenameExceptionHandler(final MissingFilenameExtensionException exception) {
		LOGGER.error(format(this.exceptionHandlerLogMessage, exception.getClass().getSimpleName()), exception);
		return ResponseEntity.badRequest().body(this.transformer.transform(exception, "The file does not contain extension"));
	}

	@ExceptionHandler(InvalidBankProcessorNameException.class)
	public ResponseEntity<ResponseExceptionContainer> invalidBankProcessorNameExceptionHandler(final InvalidBankProcessorNameException exception) {
		LOGGER.error(format(this.exceptionHandlerLogMessage, exception.getClass().getSimpleName()), exception);
		return ResponseEntity.badRequest().body(this.transformer.transform(exception, "The requested Bank Processor Name is not valid or weren't implemented yet"));
	}

	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<ResponseExceptionContainer> fileUploadExceptionHandler(final MultipartException exception) {
		LOGGER.error(format(this.exceptionHandlerLogMessage, exception.getClass().getSimpleName()), exception);
		return ResponseEntity.badRequest().body(this.transformer.transformThrowable(exception, "An error occurred on the file upload process"));
	}

}
