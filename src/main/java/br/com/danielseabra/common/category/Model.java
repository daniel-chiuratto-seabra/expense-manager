package br.com.danielseabra.common.category;

public enum Model {

	MODEL1("Model1");

	private String modelName;

	private Model(final String modelName) {
		this.modelName = modelName;
	}

	public String getModelName() {
		return this.modelName;
	}
}
