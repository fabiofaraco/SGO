package br.com.ocorrencias.dao;

import java.util.Date;
import java.util.List;

import br.com.ocorrencias.bean.Cidade;
import br.com.ocorrencias.bean.Endereco;
import br.com.ocorrencias.bean.Ocorrencia;

public interface InterfaceEnderecoDao extends Dao<Endereco> {
	public List<Cidade> carregarCidade(int id);
	
	public List<Ocorrencia> carregarEndereco(Date data);
}
