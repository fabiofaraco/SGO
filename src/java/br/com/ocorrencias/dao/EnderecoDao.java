package br.com.ocorrencias.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ocorrencias.bean.Cidade;
import br.com.ocorrencias.bean.Endereco;
import br.com.ocorrencias.bean.Ocorrencia;
import br.com.ocorrencias.util.PersistenceUtil;

public class EnderecoDao extends GenericDao<Endereco> implements InterfaceEnderecoDao {
	public EnderecoDao() {
		super(Endereco.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> carregarCidade(int id) {
		EntityManager manager = PersistenceUtil.getEntityManager();
		try {
			String hql = "select c from Cidade as c " +
					     "where c.estado.id = :id";
					
			Query query = manager.createQuery(hql);
			query.setParameter("id", id);
			
			
			return query.getResultList();

		} catch(NoResultException e) {
			return null;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> carregarEndereco(Date data) {
		EntityManager manager = PersistenceUtil.getEntityManager();
		try {
			Query query = manager.createQuery("select o from Ocorrencia as o where o.data = :data");
			query.setParameter("data", data);
			
			return query.getResultList();
		} catch(NoResultException e) {
			return null;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		
		return null;
	}

}
