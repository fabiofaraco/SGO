package br.com.ocorrencias.dao;

import br.com.ocorrencias.bean.Requerente;
import br.com.ocorrencias.bean.Usuario;
import java.util.List;

public interface InterfaceRequerenteDao extends Dao<Requerente> {
	public boolean validarCpf(String cpf, int id);
	
	public Requerente buscarCpf(String cpf);
        
        public List<Requerente> getListaFiltro(String nomeFiltro, String cpfFiltro);
}
