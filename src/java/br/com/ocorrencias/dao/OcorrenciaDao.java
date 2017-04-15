package br.com.ocorrencias.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ocorrencias.bean.Ocorrencia;
import br.com.ocorrencias.util.PersistenceUtil;

public class OcorrenciaDao extends GenericDao<Ocorrencia> implements InterfaceOcorrenciaDao {

	public OcorrenciaDao() {
		super(Ocorrencia.class);
	}

	@Override
	public boolean validarDataOcorrencia(Date data) {
		EntityManager manager = PersistenceUtil.getEntityManager();
		
		try {
			Query query = manager.createQuery("select o from Ocorrencia o where o.data = :data");
			query.setParameter("data", data);
			
			if(query.getResultList().size() == 0) {
				return false;
			}
		} finally {
			manager.close();
		}
		
		return true;
	}
}
