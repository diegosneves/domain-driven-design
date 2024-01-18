package diegosneves.ddd.github.repository;

import diegosneves.ddd.github.domain.entity.Produto;
import diegosneves.ddd.github.domain.repository.ProdutoRepository;
import diegosneves.ddd.github.exceptions.ProdutoException;
import diegosneves.ddd.github.infrastructure.db.mysql.config.HibernateConnectionSingleton;
import diegosneves.ddd.github.infrastructure.db.mysql.model.ProdutoEntity;
import diegosneves.ddd.github.mapper.BuilderMapper;
import diegosneves.ddd.github.mapper.MapperStrategy;
import diegosneves.ddd.github.mapper.ProdutoFromProdutoEntityMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class ProdutoEntityRepository implements ProdutoRepository {

    private static final String PRODUTO_NAO_ENCONTRADO = "O Produto de id [%s] informado não foi localizado";
    private static final String FALHA_AO_SALVAR_ENTIDADE_PRODUTO = "Falha ao Salvar Entidade Produto:";
    private static final String FALHA_AO_ATUALIZAR_ENTIDADE_PRODUTO = "Falha ao atualizar o Produto de ID: [ %s ]:";
    private static final String FALHA_AO_DELETAR_ENTIDADE_PRODUTO = "Falha ao deletar o Produto de ID: [ %s ]:";
    private static final String FALHA_AO_BUSCAR_TODOS_OS_PRODUTOS = "Falha ao buscar todos os produtos";

    @Override
    public void guardar(Produto entidade) {
        ProdutoEntity produtoEntity = BuilderMapper.mapper(ProdutoEntity.class, entidade);
        Session session = HibernateConnectionSingleton.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(produtoEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ProdutoException(FALHA_AO_SALVAR_ENTIDADE_PRODUTO, e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
    }

    @Override
    public void atualizar(Produto entidade) {
        ProdutoEntity produtoEntity = BuilderMapper.mapper(ProdutoEntity.class, entidade);
        Session session = HibernateConnectionSingleton.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(produtoEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ProdutoException(String.format(FALHA_AO_ATUALIZAR_ENTIDADE_PRODUTO, entidade.getId()), e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }

    }

    @Override
    public void deletar(Produto entidade) {
        ProdutoEntity produtoEntity = BuilderMapper.mapper(ProdutoEntity.class, entidade);
        Session session = HibernateConnectionSingleton.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(produtoEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ProdutoException(String.format(FALHA_AO_DELETAR_ENTIDADE_PRODUTO, entidade.getId()), e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
    }

    @Override
    public Produto buscarPorId(String id) throws ProdutoException {
        Session session = HibernateConnectionSingleton.getSession();
        MapperStrategy<Produto, ProdutoEntity> produtoFromProdutoEntity = new ProdutoFromProdutoEntityMapper();
        try {
            Query<ProdutoEntity> query = session.createQuery("FROM ProdutoEntity p where p.id = :id", ProdutoEntity.class);
            query.setParameter("id", id);
            ProdutoEntity resultado = query.uniqueResult();
            if (isNull(resultado)) {
                throw new ProdutoException(String.format(PRODUTO_NAO_ENCONTRADO, id));
            }
            return BuilderMapper.mapper(Produto.class, resultado, produtoFromProdutoEntity);
        } catch (Exception e) {
            throw new ProdutoException(String.format(PRODUTO_NAO_ENCONTRADO, id), e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
    }

    @Override
    public List<Produto> buscarTodos() {
        Session session = HibernateConnectionSingleton.getSession();
        List<Produto> produtos = new ArrayList<>();
        MapperStrategy<Produto, ProdutoEntity> produtoFromProdutoEntity = new ProdutoFromProdutoEntityMapper();
        try {
            Query<ProdutoEntity> query = session.createQuery("FROM ProdutoEntity", ProdutoEntity.class);
            produtos = query.list().stream().map(produtoEntity -> BuilderMapper.mapper(Produto.class, produtoEntity, produtoFromProdutoEntity)).toList();
        } catch (Exception e) {
            throw new ProdutoException(FALHA_AO_BUSCAR_TODOS_OS_PRODUTOS, e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
        return produtos;
    }
}
