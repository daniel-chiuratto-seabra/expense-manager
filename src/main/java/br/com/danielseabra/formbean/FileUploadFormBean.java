package br.com.danielseabra.formbean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import br.com.danielseabra.constraint.MultipartFileConstraint;

public class FileUploadFormBean {

	@NotBlank(message = "{bean.validation.notblank.message}")
	private String bankName;

	@NotBlank(message = "{bean.validation.notblank.message}")
	private String modelName;

	@NotNull(message = "{bean.validation.notnull.message}")
	@MultipartFileConstraint
	private MultipartFile file;

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return this.bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(final String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return this.modelName;
	}

	/**
	 * @param modelName
	 *            the modelName to set
	 */
	public void setModelName(final String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return this.file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(final MultipartFile file) {
		this.file = file;
	}

}
