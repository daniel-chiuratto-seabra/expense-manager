package br.com.danielseabra.constraint.validator;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.danielseabra.constraint.MultipartFileConstraint;

public class MultipartFileValidator implements ConstraintValidator<MultipartFileConstraint, MultipartFile> {

	private static final Logger LOGGER = LoggerFactory.getLogger(MultipartFileValidator.class);

	@Override
	public boolean isValid(final MultipartFile file, final ConstraintValidatorContext context) {
		if (LOGGER.isInfoEnabled())
			LOGGER.info("The MultipartFile validator has started to validate the received object");

		if (null == file) {
			if (LOGGER.isErrorEnabled())
				LOGGER.error("There is no MultipartFile object present (the object is null), not being able to be processed");
			return false;
		}
		
		final String filename = file.getOriginalFilename();
		if (StringUtils.isEmpty(filename)) {
			if (LOGGER.isErrorEnabled())
				LOGGER.error("The received MultipartFile object does not contain a filename data");
			return false;
		}

		try {
			final InputStream inputStream = file.getInputStream();
			if ((null == inputStream) || (0 == inputStream.available())) {
				if (LOGGER.isErrorEnabled())
					LOGGER.error("The received MultipartFile object contains {} InputStream object, not being able to be read",
							null == inputStream ? "a null" : "an empty");
				return false;
			}
		} catch (final IOException e) {
			if (LOGGER.isErrorEnabled())
				LOGGER.error("An error occurred during the MultipartFile object reading, and an Exception has been thrown", e);
			return false;
		}
		
		if (LOGGER.isInfoEnabled())
			LOGGER.info("The MultipartFile validator has finished the validation without errors");

		return true;
	}

}
