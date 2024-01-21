package diegosneves.ddd.github.infrastructure.product.repository.mysql;

import diegosneves.ddd.github.domain.product.entity.Produto;
import diegosneves.ddd.github.domain.product.repository.ProdutoRepository;
import diegosneves.ddd.github.exceptions.ProdutoException;
import diegosneves.ddd.github.infrastructure.shared.HibernateConnectionSingleton;
import diegosneves.ddd.github.mapper.shared.MapperStrategy;
import diegosneves.ddd.github.mapper.ProdutoEntityFromProdudoMapper;
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
    private static final String OBJETO_PRODUTO_NECESSARIO = "O Objeto Produto é obrigatório!";
    private static final String PRODUTO_ID_NULO = "ID do Produto não pode ser nulo!";

    private final MapperStrategy<ProdutoEntity, Produto> produtoEntityFromProdutoMapper = new ProdutoEntityFromProdudoMapper();
    private final MapperStrategy<Produto, ProdutoEntity> produtoFromProdutoEntityMapper = new ProdutoFromProdutoEntityMapper();

    @Override
    public void guardar(Produto entidade) {
        if (isNull(entidade)) {
            throw new ProdutoException(OBJETO_PRODUTO_NECESSARIO);
        }
        ProdutoEntity produtoEntity = this.produtoEntityFromProdutoMapper.mapper(entidade);
        Transaction transaction = null;
        try (Session session = HibernateConnectionSingleton.getSession()){
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
        if (isNull(entidade)) {
            throw new ProdutoException(OBJETO_PRODUTO_NECESSARIO);
        }
        ProdutoEntity produtoEntity = this.produtoEntityFromProdutoMapper.mapper(entidade);
        Transaction transaction = null;
        try (Session session = HibernateConnectionSingleton.getSession()){
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
        if (isNull(entidade)) {
            throw new ProdutoException(OBJETO_PRODUTO_NECESSARIO);
        }
        ProdutoEntity produtoEntity = this.produtoEntityFromProdutoMapper.mapper(entidade);
        Transaction transaction = null;
        try (Session session = HibernateConnectionSingleton.getSession()){
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
        if (isNull(id) || id.isBlank()) {
            throw new ProdutoException(PRODUTO_ID_NULO);
        }
        try (Session session = HibernateConnectionSingleton.getSession()){
            Query<ProdutoEntity> query = session.createQuery("FROM ProdutoEntity p where p.id = :id", ProdutoEntity.class);
            query.setParameter("id", id);
            ProdutoEntity resultado = query.uniqueResult();
            if (isNull(resultado)) {
                throw new ProdutoException(String.format(PRODUTO_NAO_ENCONTRADO, id));
            }
            return this.produtoFromProdutoEntityMapper.mapper(resultado);
        } catch (Exception e) {
            throw new ProdutoException(String.format(PRODUTO_NAO_ENCONTRADO, id), e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
    }

    @Override
    public List<Produto> buscarTodos() {
        List<Produto> produtos = new ArrayList<>();
        try (Session session = HibernateConnectionSingleton.getSession()){
            Query<ProdutoEntity> query = session.createQuery("FROM ProdutoEntity", ProdutoEntity.class);
            produtos = query.list().stream().map(this.produtoFromProdutoEntityMapper::mapper).toList();
        } catch (Exception e) {
            throw new ProdutoException(FALHA_AO_BUSCAR_TODOS_OS_PRODUTOS, e);
        } finally {
            HibernateConnectionSingleton.closeSessionFactory();
        }
        return produtos;
    }
}
