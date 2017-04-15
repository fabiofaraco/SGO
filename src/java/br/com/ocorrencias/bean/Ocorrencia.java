package br.com.ocorrencias.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="OCORRENCIAS")
public class Ocorrencia {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DT_OCORRENCIA")
	private Date data = new Date();
	
	@ManyToOne
	@JoinColumn(name="ID_EVENTO")
	private Evento evento = new Evento();
	
	@Embedded
	private Endereco endereco = new Endereco();
	
	@Column(name="RELATO_FATOS")
	private String relato;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getRelato() {
		return relato;
	}

	public void setRelato(String relato) {
		this.relato = relato;
	}
}
