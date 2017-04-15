package br.com.ocorrencias.dao;

import br.com.ocorrencias.bean.Usuario;

public interface InterfaceUsuarioDao extends Dao<Usuario> {
	public Usuario realizaLogin(String email, String senha);
	
	public boolean validarCpf(String cpf, int id);
}
