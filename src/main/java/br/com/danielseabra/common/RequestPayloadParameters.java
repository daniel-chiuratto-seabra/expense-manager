package br.com.danielseabra.common;

public enum RequestPayloadParameters {

	BANK_NAME("bankName"),
	FILE_EXTENSION_NAME("fileExtensionName"),
	MODEL_NAME("modelName"),
	FILE("file");

	private String parameterName;

	private RequestPayloadParameters(final String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterName() {
		return this.parameterName;
	}
}
