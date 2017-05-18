package br.com.ocorrencias.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ocorrencias.bean.Requerente;
import br.com.ocorrencias.bean.Usuario;
import br.com.ocorrencias.util.PersistenceUtil;
import java.util.List;

public class RequerenteDao extends GenericDao<Requerente> implements InterfaceRequerenteDao {

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
        } catch (NoResultException e) {
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

            return (Requerente) query.getSingleResult();

        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return null;
    }

    @Override
    public List<Requerente> getListaFiltro(String nomeFiltro, String cpfFiltro) {
        EntityManager manager = PersistenceUtil.getEntityManager();

        try {
            final String VAZIO = "";

            String sql = "select r from Requerente r\n";

            if (!nomeFiltro.equals(VAZIO) && cpfFiltro.equals(VAZIO)) {
                sql += "where upper(r.nome) like concat('%', upper(:nomeFiltro), '%')\n";
            } else if (!cpfFiltro.equals(VAZIO) && nomeFiltro.equals(VAZIO)) {
                sql += "where r.cpf = :cpfFiltro\n";
            } else if (!nomeFiltro.equals(VAZIO) && !cpfFiltro.equals(VAZIO)) {
                sql += "where upper(r.nome) like concat('%', upper(:nomeFiltro), '%')"
                        + "and r.cpf = :cpfFiltro\n";
            }

            sql += "order by r.nome";

            Query query = manager.createQuery(sql);

            if (!nomeFiltro.equals(VAZIO)) {
                query.setParameter("nomeFiltro", nomeFiltro);
            }

            if (!cpfFiltro.equals(VAZIO)) {
                query.setParameter("cpfFiltro", cpfFiltro);
            }

            return query.getResultList();
        } finally {
            manager.close();
        }
    }

}
