package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.entity.ItemPedido;
import diegosneves.ddd.github.infrastructure.db.mysql.model.ItemPedidoEntity;

public class ItemPedidoFromItemPedidoEntity implements MapperStrategy<ItemPedido, ItemPedidoEntity> {

    @Override
    public ItemPedido mapper(ItemPedidoEntity source) {
        return new ItemPedido(
                source.getId(),
                source.getName(),
                source.getPreco(),
                source.getProdutoId(),
                source.getQuantidade());
    }

}
