package br.com.ocorrencias.bean;

import javax.persistence.Column;

public class Telefone {
	
	@Column(name="TEL_RESIDENCIAL")
	private String residencial;
	
	@Column(name="TEL_CELULAR")
	private String celular;
	public String getResidencial() {
		return residencial;
	}
	public void setResidencial(String residencial) {
		this.residencial = residencial;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
}
