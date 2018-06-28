package br.com.danielseabra.common.category;

public enum FileExtension {

	CSV("CSV"),
	XLS("XLS"),
	XLSX("XLSX");

	private String fileExtensionName;

	private FileExtension(final String fileExtensionName) {
		this.fileExtensionName = fileExtensionName;
	}

	public String getFileExtensionName() {
		return this.fileExtensionName;
	}
}
