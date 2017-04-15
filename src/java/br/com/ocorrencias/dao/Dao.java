package br.com.ocorrencias.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T> {
	void incluir(T t);
	void alterar(T t);
	void remover(Serializable id);
	List<T> getLista(String query);
	T buscar(Serializable id);
}
