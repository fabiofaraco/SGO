package br.com.ocorrencias.bean;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CIDADES")
public class Cidade {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="codigo_ibge")
	private int codigoIbge;
	
	@ManyToOne
	@JoinColumn(name="estado_id")
	private Estado estado = new Estado(); 
	
	@Column(name="populacao_2010")
	private int populacao;
	
	@Column(name="densidade_demo")
	private BigDecimal densidadeDemografica;
	
	@Column(name="gentilico")
	private String gentilico; 
	
	@Column(name="area")
	private BigDecimal area;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(int codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getPopulacao() {
		return populacao;
	}

	public void setPopulacao(int populacao) {
		this.populacao = populacao;
	}

	public BigDecimal getDensidadeDemografica() {
		return densidadeDemografica;
	}

	public void setDensidadeDemografica(BigDecimal densidadeDemografica) {
		this.densidadeDemografica = densidadeDemografica;
	}

	public String getGentilico() {
		return gentilico;
	}

	public void setGentilico(String gentilico) {
		this.gentilico = gentilico;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}
}
