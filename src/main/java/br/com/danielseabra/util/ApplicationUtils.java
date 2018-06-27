package br.com.danielseabra.util;

import org.springframework.web.multipart.MultipartFile;

public class ApplicationUtils {

	public static String getBankProcessorName(final String bankName, final String fileExtensionName, final String modelName) {
		return new StringBuilder(bankName.toLowerCase())
				.append(fileExtensionName.toUpperCase())
				.append(getCamelCasedString(modelName))
				.toString();
	}

	public static String getFileExtensionFromMultipartFile(final MultipartFile file) {
		return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
	}

	public static String getCamelCasedString(String value) {
		value = value.toLowerCase();
		value = value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
		return value;
	}
}
