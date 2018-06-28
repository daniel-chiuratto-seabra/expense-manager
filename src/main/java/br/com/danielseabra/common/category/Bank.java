package br.com.danielseabra.common.category;

public enum Bank {

	BRADESCO("Bradesco"),
	ITAU("Itau");

	private String bankName;

	private Bank(final String bankName) {
		this.bankName = bankName;
	}

	public String getBankName() {
		return this.bankName;
	}
}
