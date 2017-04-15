package br.com.ocorrencias.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PROTOCOLOS")
public class Protocolo {
	 
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@OneToOne
	@JoinColumn(name="ID_REQUERENTE")
	private Requerente requerente = new Requerente();
	
	@OneToOne
	@JoinColumn(name="ID_OCORRENCIA")
	private Ocorrencia ocorrencia = new Ocorrencia();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Requerente getRequerente() {
		return requerente;
	}

	public void setRequerente(Requerente requerente) {
		this.requerente = requerente;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}
}
