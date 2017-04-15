package br.com.ocorrencias.dao;

import br.com.ocorrencias.bean.Requerente;

public interface InterfaceRequerenteDao extends Dao<Requerente> {
	public boolean validarCpf(String cpf, int id);
	
	public Requerente buscarCpf(String cpf);
}
