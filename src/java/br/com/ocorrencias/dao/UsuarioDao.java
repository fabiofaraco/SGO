package br.com.ocorrencias.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.ocorrencias.bean.Usuario;
import br.com.ocorrencias.util.PersistenceUtil;
import java.util.List;

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

            Usuario usuario = (Usuario) query.getSingleResult();

            return usuario;
        } catch (NoResultException e) {
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
        } catch (NoResultException e) {
            return true;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Usuario> getListaFiltro(String nomeFiltro, String cpfFiltro) {
        EntityManager manager = PersistenceUtil.getEntityManager();

        try {
            final String VAZIO = "";

            String sql = "select u from Usuario u\n";

            if (!nomeFiltro.equals(VAZIO) && cpfFiltro.equals(VAZIO)) {
                sql += "where upper(u.nome) like concat('%', upper(:nomeFiltro), '%')\n";
            } else if (!cpfFiltro.equals(VAZIO) && nomeFiltro.equals(VAZIO)) {
                sql += "where u.cpf = :cpfFiltro\n";
            } else if (!nomeFiltro.equals(VAZIO) && !cpfFiltro.equals(VAZIO)) {
                sql += "where upper(u.nome) like concat('%', upper(:nomeFiltro), '%')"
                        + "and u.cpf = :cpfFiltro\n";
            }
            
            sql += "order by u.nome";

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
