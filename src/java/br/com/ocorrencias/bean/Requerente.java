package br.com.ocorrencias.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="REQUERENTES")
public class Requerente {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="SOBRENOME")
	private String sobrenome;
	
	@Column(name="CPF")
	private String cpf;
	
	@Column(name="EMAIL")
	private String email;
	
	@Embedded
	private Endereco endereco = new Endereco();
	
	@Embedded
	private Telefone telefone = new Telefone();
	
	@Temporal(TemporalType.DATE)
	@Column(name="DT_REQUISICAO")
	private Date dataRequisicao = new Date();
	
	@Temporal(TemporalType.DATE)
	@Column(name="DT_NASCIMENTO")
	private Date dataNascimento;
		
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
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public Date getDataRequisicao() {
		return dataRequisicao;
	}
	public void setDataRequisicao(Date dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
