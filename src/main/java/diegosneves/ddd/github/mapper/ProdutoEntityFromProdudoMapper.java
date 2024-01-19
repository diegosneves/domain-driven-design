package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.entity.Produto;
import diegosneves.ddd.github.infrastructure.db.mysql.model.ProdutoEntity;

public class ProdutoEntityFromProdudoMapper implements MapperStrategy<ProdutoEntity, Produto> {

    @Override
    public ProdutoEntity mapper(Produto source) {
        return new ProdutoEntity(source.getId(), source.getNome(), source.getPreco());
    }
}
