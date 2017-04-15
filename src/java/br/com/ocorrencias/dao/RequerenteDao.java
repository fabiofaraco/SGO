package br.com.ocorrencias.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ocorrencias.bean.Requerente;
import br.com.ocorrencias.util.PersistenceUtil;

public class RequerenteDao extends GenericDao<Requerente> implements InterfaceRequerenteDao{

	public RequerenteDao() {
		super(Requerente.class);
	}
	
	@Override
	public boolean validarCpf(String cpf, int id) {
		EntityManager manager = PersistenceUtil.getEntityManager();
		try {
			Query query = manager.createQuery("select r from Requerente r where r.cpf = :cpf and r.id != :id");
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

	@Override
	public Requerente buscarCpf(String cpf) {
		EntityManager manager = PersistenceUtil.getEntityManager();
		try {
			Query query = manager.createQuery("select r from Requerente r where r.cpf = :cpf");
			query.setParameter("cpf", cpf);
			
			return (Requerente)query.getSingleResult();
			
		} catch(NoResultException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		
		return null;
	}

}
