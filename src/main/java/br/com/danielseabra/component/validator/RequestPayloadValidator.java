package br.com.danielseabra.component.validator;

import org.springframework.web.multipart.MultipartFile;

import br.com.danielseabra.exception.inner.impl.MissingFilenameExtensionException;
import br.com.danielseabra.exception.inner.impl.MissingParameterException;

public interface RequestPayloadValidator {

	void validateUploadRequestPayload(MultipartFile file, String bankName, String modelName) throws MissingParameterException, MissingFilenameExtensionException;

}
