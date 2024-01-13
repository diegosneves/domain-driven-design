package diegosneves.ddd.github.repository;

import diegosneves.ddd.github.domain.entity.Produto;
import diegosneves.ddd.github.domain.repository.ProdutoRepository;
import diegosneves.ddd.github.infrastructure.db.mysql.config.HibernateConnectionSingleton;
import diegosneves.ddd.github.infrastructure.db.mysql.model.ProdutoEntity;
import diegosneves.ddd.github.mapper.BuilderMapper;
import diegosneves.ddd.github.mapper.MapperStrategy;
import diegosneves.ddd.github.mapper.ProdutoEntityFromProdutoMapper;
import diegosneves.ddd.github.mapper.ProdutoFromProdutoEntityMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProdutoEntityRepository implements ProdutoRepository {

    @Override
    public void guardar(Produto entidade) {
        MapperStrategy<ProdutoEntity, Produto> produtoEntityFromProduto = new ProdutoEntityFromProdutoMapper();
        ProdutoEntity produtoEntity = BuilderMapper.mapper(ProdutoEntity.class, entidade, produtoEntityFromProduto);
        Session session = HibernateConnectionSingleton.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(produtoEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
    }

    @Override
    public void atualizar(Produto entidade) {
        MapperStrategy<ProdutoEntity, Produto> produtoEntityFromProduto = new ProdutoEntityFromProdutoMapper();
        ProdutoEntity produtoEntity = BuilderMapper.mapper(ProdutoEntity.class, entidade, produtoEntityFromProduto);
        Session session = HibernateConnectionSingleton.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(produtoEntity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }

    }

    @Override
    public Produto buscarPorId(String id) {
        Session session = HibernateConnectionSingleton.getSession();
        MapperStrategy<Produto, ProdutoEntity> produtoFromProdutoEntity = new ProdutoFromProdutoEntityMapper();
        try {
            Query<ProdutoEntity> query = session.createQuery("FROM ProdutoEntity p where p.id = :id", ProdutoEntity.class);
            query.setParameter("id", id);
            return BuilderMapper.mapper(Produto.class, query.uniqueResult(), produtoFromProdutoEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
        return null;
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
            e.printStackTrace();
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
        return produtos;
    }
}
