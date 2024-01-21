package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.product.entity.Produto;
import diegosneves.ddd.github.infrastructure.product.repository.mysql.ProdutoEntity;
import diegosneves.ddd.github.mapper.shared.MapperStrategy;

public class ProdutoEntityFromProdudoMapper implements MapperStrategy<ProdutoEntity, Produto> {

    @Override
    public ProdutoEntity mapper(Produto source) {
        return new ProdutoEntity(source.getId(), source.getNome(), source.getPreco());
    }
}
