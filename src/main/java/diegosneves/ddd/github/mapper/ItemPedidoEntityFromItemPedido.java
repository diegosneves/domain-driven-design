package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.checkout.entity.ItemPedido;
import diegosneves.ddd.github.infrastructure.order.repository.mysql.ItemPedidoEntity;
import diegosneves.ddd.github.mapper.shared.MapperStrategy;

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
