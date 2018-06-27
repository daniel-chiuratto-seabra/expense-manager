package br.com.danielseabra.component.validator.impl;

import static br.com.danielseabra.common.RequestPayloadParameters.BANK_NAME;
import static br.com.danielseabra.common.RequestPayloadParameters.MODEL_NAME;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.danielseabra.component.validator.RequestPayloadValidator;
import br.com.danielseabra.exception.inner.impl.MissingFilenameExtensionException;
import br.com.danielseabra.exception.inner.impl.MissingParameterException;

@Component
public class RequestStatementPayloadValidatorImpl implements RequestPayloadValidator {

	@Override
	public void validateUploadRequestPayload(final MultipartFile file, final String bankName, final String modelName) throws MissingParameterException, MissingFilenameExtensionException {
		if (StringUtils.isEmpty(bankName) || StringUtils.isEmpty(modelName))
			throw new MissingParameterException(this.buildParameterArray(bankName, modelName));
		if (-1 == file.getOriginalFilename().indexOf("."))
			throw new MissingFilenameExtensionException();
	}

	private String[] buildParameterArray(final String bankName, final String modelName) {
		final Collection<String> parameterCollection = new ArrayList<>();
		if (StringUtils.isEmpty(bankName)) {
			parameterCollection.add(BANK_NAME.getParameterName());
		}
		if (StringUtils.isEmpty(modelName)) {
			parameterCollection.add(MODEL_NAME.getParameterName());
		}
		return parameterCollection.toArray(new String[parameterCollection.size()]);
	}

}
