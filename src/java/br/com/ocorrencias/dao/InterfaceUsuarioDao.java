package br.com.ocorrencias.dao;

import br.com.ocorrencias.bean.Usuario;
import java.util.List;

public interface InterfaceUsuarioDao extends Dao<Usuario> {

    public Usuario realizaLogin(String email, String senha);

    public boolean validarCpf(String cpf, int id);

    public List<Usuario> getListaFiltro(String nomeFiltro, String cpfFiltro);
}
