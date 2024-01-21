package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.product.entity.Produto;
import diegosneves.ddd.github.infrastructure.product.repository.mysql.ProdutoEntity;
import diegosneves.ddd.github.mapper.shared.MapperStrategy;

public class ProdutoFromProdutoEntityMapper implements MapperStrategy<Produto, ProdutoEntity> {

    @Override
    public Produto mapper(ProdutoEntity source) {
        return new Produto(source.getId(), source.getNome(), source.getPreco());
    }

}
