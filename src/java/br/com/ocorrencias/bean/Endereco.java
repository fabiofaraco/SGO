package br.com.ocorrencias.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Endereco implements Serializable {
	
	@Column(name="LOGRADOURO")
	private String logradouro;
	
	@Column(name="COMPLEMENTO")
	private String complemento;
	
	@ManyToOne
	@JoinColumn(name="ID_CIDADE")
	private Cidade cidade = new Cidade();
	
	@Column(name="BAIRRO")
	private String bairro;
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
