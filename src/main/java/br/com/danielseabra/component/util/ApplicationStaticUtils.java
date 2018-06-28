package br.com.danielseabra.component.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danielseabra.common.ApplicationConstants;

@Component
public class ApplicationStaticUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStaticUtils.class);

	public static String getBankProcessorName(final String bankName, final String fileExtensionName, final String modelName) {
		return new StringBuilder(bankName.toLowerCase()).append(fileExtensionName.toUpperCase())
				.append(ApplicationStaticUtils.getCamelCasedString(modelName)).toString();
	}

	public static String getFileExtensionFromMultipartFile(final MultipartFile file) {
		return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
	}

	public static String getCamelCasedString(String value) {
		value = value.toLowerCase();
		value = value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
		return value;
	}

	public static String buildMissingParametersStringFromBindingResult(final BindingResult bindingResult) {
		final StringBuilder sb = new StringBuilder();
		bindingResult.getFieldErrors().forEach(e -> {
			if (sb.length() > 0) {
				sb.append(ApplicationConstants.PARAMETER_SEPARATOR).append(ApplicationConstants.STRING_SPACE);
			}
			sb.append(e.getField()).append(ApplicationConstants.KEY_VALUE_PAIR_SEPARATOR).append(e.getDefaultMessage());
		});
		return sb.toString();
	}

	public static String[] buildMissingParametersStringArrayFromBindingResult(final BindingResult bindingResult) {
		final List<FieldError> errors = bindingResult.getFieldErrors();
		final String[] stringArr = new String[errors.size()];
		for (int i = 0; i < errors.size(); i++) {
			final FieldError fieldError = errors.get(i);
			stringArr[i] = new StringBuilder(fieldError.getField()).append(ApplicationConstants.KEY_VALUE_PAIR_SEPARATOR)
					.append(fieldError.getDefaultMessage()).toString();
		}
		return stringArr;
	}

	// -----------------------------------------------------------------------
	/**
	 * Following code was copied from {@code ExceptionUtils} class of
	 * {@code Apache Commons} lib
	 * <p>
	 * Gets the stack trace from a Throwable as a String.
	 * </p>
	 *
	 * <p>
	 * The result of this method vary by JDK version as this method
	 * uses {@link Throwable#printStackTrace(java.io.PrintWriter)}.
	 * On JDK1.3 and earlier, the cause exception will not be shown
	 * unless the specified throwable alters printStackTrace.
	 * </p>
	 *
	 * @param throwable
	 *            the <code>Throwable</code> to be examined
	 * @return the stack trace as generated by the exception's
	 *         <code>printStackTrace(PrintWriter)</code> method
	 */
	public static String getStackTrace(final Throwable throwable) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw, true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}

	public static String serializeBindingResultToMapString(final BindingResult bindingResult) {
		final Map<String, String> keyValuePairs = new HashMap<>();
		bindingResult.getFieldErrors().stream().filter(f -> !StringUtils.isEmpty(f.getDefaultMessage()))
		.forEach(f -> keyValuePairs.put(f.getField(), f.getDefaultMessage()));
		try {
			return new ObjectMapper().writeValueAsString(keyValuePairs);
		} catch (final JsonProcessingException e) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("An error occurred during the serialization from Map generated from BindingResult object into String Map", e);
			}
			return "BindingResult to String Map serialization error, see error logs for more detailed information about";
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> deserializeMapStringIntoMap(final String stringMap) {
		try {
			return new ObjectMapper().readValue(stringMap, Map.class);
		} catch (final IOException e) {
			if (LOGGER.isErrorEnabled()) {
				LOGGER.error("An error occured during the deserialization of the String Map into Map using ObjectMapper", e);
			}
			return Collections.emptyMap();
		}
	}
}