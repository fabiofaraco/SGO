package br.com.ocorrencias.dao;

import java.util.Date;

import br.com.ocorrencias.bean.Ocorrencia;

public interface InterfaceOcorrenciaDao extends Dao<Ocorrencia> {
	public boolean validarDataOcorrencia(Date data);
}
