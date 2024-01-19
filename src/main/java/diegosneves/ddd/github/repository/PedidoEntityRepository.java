package diegosneves.ddd.github.repository;

import diegosneves.ddd.github.domain.entity.Pedido;
import diegosneves.ddd.github.domain.repository.PedidoRepository;
import diegosneves.ddd.github.exceptions.PedidoException;
import diegosneves.ddd.github.infrastructure.db.mysql.config.HibernateConnectionSingleton;
import diegosneves.ddd.github.infrastructure.db.mysql.model.PedidoEntity;
import diegosneves.ddd.github.mapper.MapperStrategy;
import diegosneves.ddd.github.mapper.PedidoEntityFromPedidoMapper;
import diegosneves.ddd.github.mapper.PedidoFromPedidoEntityMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class PedidoEntityRepository implements PedidoRepository {

    private static final String OBJETO_PEDIDO_NULO = "O objeto Pedido passado para o método está nulo.";
    private static final String ERROR_SAVING_PEDIDO_ENTITY = "Falha ao salvar a entidade Pedido na base de dados";
    private static final String FALHA_ATUALIZACAO_PEDIDO = "Falha ao atualizar o Pedido [ID: %s]";
    private static final String ERROR_DELETAR_PEDIDO = "Falha ao deletar o Pedido [ID: %s]";
    private static final String ID_NAO_ENCONTRADO = "O ID [%s] não foi localizado na base de dados";
    private static final String ERRO_AO_BUSCAR_PEDIDO = "Erro ao buscar pedido por ID";
    private static final String ID_PEDIDO_OBRIGATORIO = "ID do pedido não pode ser nulo";

    private final MapperStrategy<PedidoEntity, Pedido> pedidoEntityFromPedido = new PedidoEntityFromPedidoMapper();
    private final MapperStrategy<Pedido, PedidoEntity> pedidoFromPedidoEntity = new PedidoFromPedidoEntityMapper();

    @Override
    public void guardar(Pedido entidade) {
        if (isNull(entidade)) {
            throw new PedidoException(OBJETO_PEDIDO_NULO);
        }
        PedidoEntity pedidoEntity = this.pedidoEntityFromPedido.mapper(entidade);
        Transaction transaction = null;
        try (Session session = HibernateConnectionSingleton.getSession()) {
            transaction = session.beginTransaction();
            session.persist(pedidoEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new PedidoException(ERROR_SAVING_PEDIDO_ENTITY, e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }

    }

    @Override
    public void atualizar(Pedido entidade) {
        if (isNull(entidade)) {
            throw new PedidoException(OBJETO_PEDIDO_NULO);
        }
        PedidoEntity pedidoEntity = this.pedidoEntityFromPedido.mapper(entidade);
        Transaction transaction = null;
        try (Session session = HibernateConnectionSingleton.getSession()) {
            transaction = session.beginTransaction();
            session.merge(pedidoEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new PedidoException(String.format(FALHA_ATUALIZACAO_PEDIDO, entidade.getId()), e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }

    }

    @Override
    public void deletar(Pedido entidade) {
        if (isNull(entidade)) {
            throw new PedidoException(OBJETO_PEDIDO_NULO);
        }
        PedidoEntity pedidoEntity = this.pedidoEntityFromPedido.mapper(entidade);
        Transaction transaction = null;
        try (Session session = HibernateConnectionSingleton.getSession()) {
            transaction = session.beginTransaction();
            session.remove(pedidoEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new PedidoException(ERROR_DELETAR_PEDIDO, e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
    }

    @Override
    public Pedido buscarPorId(String id) {
        if (isNull(id) || id.isBlank()) {
            throw new PedidoException(ID_PEDIDO_OBRIGATORIO);
        }
        Transaction transaction = null;
        try (Session session = HibernateConnectionSingleton.getSession()) {
            transaction = session.beginTransaction();
            Query<PedidoEntity> query = session.createQuery("FROM PedidoEntity p WHERE p.id = :id", PedidoEntity.class);
            query.setParameter("id", id);
            PedidoEntity pedidoEntity = query.uniqueResult();
            if (isNull(pedidoEntity)) {
                throw new PedidoException(String.format(ID_NAO_ENCONTRADO, id));
            }
            transaction.commit();
            return this.pedidoFromPedidoEntity.mapper(pedidoEntity);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new PedidoException(ERRO_AO_BUSCAR_PEDIDO, e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
    }

    @Override
    public List<Pedido> buscarTodos() {
        Transaction transaction = null;
        List<Pedido> pedidos = new ArrayList<>();
        try (Session session = HibernateConnectionSingleton.getSession()) {
            transaction = session.beginTransaction();
            Query<PedidoEntity> query = session.createQuery("FROM PedidoEntity", PedidoEntity.class);
            List<PedidoEntity> pedidoEntities = query.list();
            transaction.commit();
            pedidos = pedidoEntities.stream().map(this.pedidoFromPedidoEntity::mapper).toList();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new PedidoException(ERRO_AO_BUSCAR_PEDIDO, e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
        return pedidos;
    }
}
