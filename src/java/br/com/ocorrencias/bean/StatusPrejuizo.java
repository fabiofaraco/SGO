package br.com.ocorrencias.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class StatusPrejuizo {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="DESCRICAO")
	private String descricao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
