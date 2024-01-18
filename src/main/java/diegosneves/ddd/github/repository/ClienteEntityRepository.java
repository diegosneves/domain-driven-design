package diegosneves.ddd.github.repository;

import diegosneves.ddd.github.domain.entity.Cliente;
import diegosneves.ddd.github.domain.repository.ClienteRepository;
import diegosneves.ddd.github.exceptions.ClienteException;
import diegosneves.ddd.github.infrastructure.db.mysql.config.HibernateConnectionSingleton;
import diegosneves.ddd.github.infrastructure.db.mysql.model.ClienteEntity;
import diegosneves.ddd.github.mapper.BuilderMapper;
import diegosneves.ddd.github.mapper.ClienteEntityFromClienteMapper;
import diegosneves.ddd.github.mapper.ClienteFromClienteEntityMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class ClienteEntityRepository implements ClienteRepository {

    private static final String FALHA_AO_SALVAR_ENTIDADE_CLIENTE = "Falha ao Salvar Entidade Cliente:";
    private static final String FALHA_AO_ATUALIZAR_O_CLIENTE = "Falha ao atualizar o Cliente  de ID: [ %s ]:";
    private static final String FALHA_AO_DELETAR_O_CLIENTE = "Falha ao deletar o Cliente de ID: [ %s ]:";
    private static final String CLIENTE_BUSCA_ID_ERRO = "Falha ao buscar o cliente por ID: [ %s ]";
    private static final String FALHA_AO_BUSCAR_TODOS_OS_CLIENTES = "Falha ao buscar todos os clientes";
    private static final String CLIENTE_NULO_EXCEPTION_MESSAGE = "O objeto Cliente passado para o método está nulo. A operação requer um Cliente válido.";

    @Override
    public void guardar(Cliente entidade) {
        if (isNull(entidade)) {
            throw new ClienteException(CLIENTE_NULO_EXCEPTION_MESSAGE);
        }
        ClienteEntityFromClienteMapper mapper =new ClienteEntityFromClienteMapper();
        ClienteEntity entity = BuilderMapper.mapper(ClienteEntity.class, entidade, mapper);
        Session session = HibernateConnectionSingleton.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ClienteException(FALHA_AO_SALVAR_ENTIDADE_CLIENTE, e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
    }

    @Override
    public void atualizar(Cliente entidade) {
        if (isNull(entidade)) {
            throw new ClienteException(CLIENTE_NULO_EXCEPTION_MESSAGE);
        }
        ClienteEntityFromClienteMapper mapper =new ClienteEntityFromClienteMapper();
        ClienteEntity entity = BuilderMapper.mapper(ClienteEntity.class, entidade, mapper);
        Session session = HibernateConnectionSingleton.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ClienteException(String.format(FALHA_AO_ATUALIZAR_O_CLIENTE, entidade.getId()), e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }

    }

    @Override
    public void deletar(Cliente entidade) {
        if (isNull(entidade)) {
            throw new ClienteException(CLIENTE_NULO_EXCEPTION_MESSAGE);
        }
        ClienteEntityFromClienteMapper mapper = new ClienteEntityFromClienteMapper();
        ClienteEntity entity = BuilderMapper.mapper(ClienteEntity.class, entidade, mapper);
        Session session = HibernateConnectionSingleton.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ClienteException(String.format(FALHA_AO_DELETAR_O_CLIENTE, entidade.getId()), e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
    }

    @Override
    public Cliente buscarPorId(String id) {
        Session session = HibernateConnectionSingleton.getSession();
        ClienteFromClienteEntityMapper mapper = new ClienteFromClienteEntityMapper();
        try {
            Query<ClienteEntity> query = session.createQuery("FROM ClienteEntity p where p.id = :id", ClienteEntity.class);
            query.setParameter("id", id);
            ClienteEntity resultado = query.uniqueResult();
            if (isNull(resultado)) {
                throw new ClienteException(String.format(CLIENTE_BUSCA_ID_ERRO, id));
            }
            return BuilderMapper.mapper(Cliente.class, resultado, mapper);
        } catch (Exception e) {
            throw new ClienteException(String.format(CLIENTE_BUSCA_ID_ERRO, id), e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
    }

    @Override
    public List<Cliente> buscarTodos() {
        Session session = HibernateConnectionSingleton.getSession();
        List<Cliente> clientes = new ArrayList<>();
        ClienteFromClienteEntityMapper mapper = new ClienteFromClienteEntityMapper();
        try {
            Query<ClienteEntity> query = session.createQuery("FROM ClienteEntity ", ClienteEntity.class);
            clientes = query.list().stream().map(clienteEntity -> BuilderMapper.mapper(Cliente.class, clienteEntity, mapper)).toList();
        } catch (Exception e) {
            throw new ClienteException(FALHA_AO_BUSCAR_TODOS_OS_CLIENTES, e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
        return clientes;
    }

}
