package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.entity.ItemPedido;
import diegosneves.ddd.github.infrastructure.db.mysql.model.ItemPedidoEntity;

public class ItemPedidoEntityFromItemPedido implements MapperStrategy<ItemPedidoEntity, ItemPedido> {

    @Override
    public ItemPedidoEntity mapper(ItemPedido source) {
        return new ItemPedidoEntity.Builder()
                .id(source.getId())
                .name(source.getName())
                .preco(source.getPreco())
                .quantidade(source.getQuantidade())
                .produtoId(source.getProdutoId())
                .build();
    }

}
