package br.com.ocorrencias.bean;

import br.com.fabio.entity.Endereco;
import java.io.Serializable;
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
@Table(name = "OCORRENCIAS")
public class Ocorrencia implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA")
    private Date data = new Date();

    @Column(name = "RESPONSAVEL")
    private String responsavel;

    @ManyToOne
    @JoinColumn(name = "ID_EVENTO")
    private Evento evento = new Evento();

    @Embedded
    private Endereco endereco = new Endereco();

    @Column(name = "RELATO")
    private String relato;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the responsavel
     */
    public String getResponsavel() {
        return responsavel;
    }

    /**
     * @param responsavel the responsavel to set
     */
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    /**
     * @return the evento
     */
    public Evento getEvento() {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the relato
     */
    public String getRelato() {
        return relato;
    }

    /**
     * @param relato the relato to set
     */
    public void setRelato(String relato) {
        this.relato = relato;
    }
}
