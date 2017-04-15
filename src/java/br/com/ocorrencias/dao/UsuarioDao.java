package br.com.ocorrencias.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ocorrencias.bean.Usuario;
import br.com.ocorrencias.util.PersistenceUtil;

public class UsuarioDao extends GenericDao<Usuario> implements InterfaceUsuarioDao {

	public UsuarioDao() {
		super(Usuario.class);
	}

	@Override
	public Usuario realizaLogin(String email, String senha) {
		EntityManager manager = PersistenceUtil.getEntityManager();
		
		try {
			Query query = manager.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha");
			query.setParameter("email", email);
			query.setParameter("senha", senha);
			
			Usuario usuario = (Usuario)query.getSingleResult();
			
			return usuario;
		} catch(NoResultException e) {
			return null;
		} finally {
			manager.close();
		}
	}
	
	@Override
	public boolean validarCpf(String cpf, int id) {
		EntityManager manager = PersistenceUtil.getEntityManager();
		try {
			Query query = manager.createQuery("select u from Usuario u where u.cpf = :cpf and u.id != :id");
			query.setParameter("cpf", cpf);
			query.setParameter("id", id);
			query.getSingleResult();
			
			return false;
		} catch(NoResultException e) {
			return true;
		} finally {
			manager.close();
		}
	}

}
