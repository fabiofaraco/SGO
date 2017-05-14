package br.com.ocorrencias.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "ID_PERFIL")
    private Perfil perfil = new Perfil();

    @Transient
    private String nomeFiltro;

    @Transient
    private String cpfFiltro;

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
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the cpfFiltro
     */
    public String getCpfFiltro() {
        return cpfFiltro;
    }

    /**
     * @param cpfFiltro the cpfFiltro to set
     */
    public void setCpfFiltro(String cpfFiltro) {
        this.cpfFiltro = cpfFiltro;
    }

    /**
     * @return the nomeFiltro
     */
    public String getNomeFiltro() {
        return nomeFiltro;
    }

    /**
     * @param nomeFiltro the nomeFiltro to set
     */
    public void setNomeFiltro(String nomeFiltro) {
        this.nomeFiltro = nomeFiltro;
    }
}
