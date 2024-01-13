package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.entity.Produto;
import diegosneves.ddd.github.infrastructure.db.mysql.model.ProdutoEntity;

public class ProdutoFromProdutoEntityMapper implements MapperStrategy<Produto, ProdutoEntity> {

    @Override
    public Produto mapper(ProdutoEntity source) {
        return new Produto(source.getId(), source.getNome(), source.getPreco());
    }

}
