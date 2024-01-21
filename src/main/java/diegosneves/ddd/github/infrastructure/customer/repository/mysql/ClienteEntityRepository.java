package diegosneves.ddd.github.infrastructure.customer.repository.mysql;

import diegosneves.ddd.github.domain.customer.entity.Cliente;
import diegosneves.ddd.github.domain.shared.event.DistribuidorEventos;
import diegosneves.ddd.github.domain.customer.event.EventoCriarCliente;
import diegosneves.ddd.github.domain.customer.repository.ClienteRepository;
import diegosneves.ddd.github.exceptions.ClienteException;
import diegosneves.ddd.github.infrastructure.shared.HibernateConnectionSingleton;
import diegosneves.ddd.github.mapper.ClienteEntityFromClienteMapper;
import diegosneves.ddd.github.mapper.ClienteFromClienteEntityMapper;
import diegosneves.ddd.github.mapper.shared.MapperStrategy;
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
    private static final String CLIENTE_ID_NULO = "O ID do Cliente não pode ser nulo.";

    private final MapperStrategy<ClienteEntity, Cliente> clienteEntityFromClienteMapper = new ClienteEntityFromClienteMapper();
    private final MapperStrategy<Cliente, ClienteEntity> clienteFromClienteEntityMapper = new ClienteFromClienteEntityMapper();


    @Override
    public void guardar(Cliente entidade) {
        if (isNull(entidade)) {
            throw new ClienteException(CLIENTE_NULO_EXCEPTION_MESSAGE);
        }
        ClienteEntity entity = this.clienteEntityFromClienteMapper.mapper(entidade);
        Transaction transaction = null;
        try(Session session = HibernateConnectionSingleton.getSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            DistribuidorEventos.notificar(new EventoCriarCliente(entidade));
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
        ClienteEntity entity = this.clienteEntityFromClienteMapper.mapper(entidade);
        Transaction transaction = null;
        try(Session session = HibernateConnectionSingleton.getSession()) {
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
        ClienteEntity entity = this.clienteEntityFromClienteMapper.mapper(entidade);
        Transaction transaction = null;
        try(Session session = HibernateConnectionSingleton.getSession()) {
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
        if (isNull(id) || id.isBlank()) {
            throw new ClienteException(CLIENTE_ID_NULO);
        }
        try (Session session = HibernateConnectionSingleton.getSession()) {
            Query<ClienteEntity> query = session.createQuery("FROM ClienteEntity p where p.id = :id", ClienteEntity.class);
            query.setParameter("id", id);
            ClienteEntity resultado = query.uniqueResult();
            if (isNull(resultado)) {
                throw new ClienteException(String.format(CLIENTE_BUSCA_ID_ERRO, id));
            }
            return this.clienteFromClienteEntityMapper.mapper(resultado);
        } catch (Exception e) {
            throw new ClienteException(String.format(CLIENTE_BUSCA_ID_ERRO, id), e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
    }

    @Override
    public List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        try(Session session = HibernateConnectionSingleton.getSession()) {
            Query<ClienteEntity> query = session.createQuery("FROM ClienteEntity ", ClienteEntity.class);
            clientes = query.list().stream().map(this.clienteFromClienteEntityMapper::mapper).toList();
        } catch (Exception e) {
            throw new ClienteException(FALHA_AO_BUSCAR_TODOS_OS_CLIENTES, e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
        return clientes;
    }

}
