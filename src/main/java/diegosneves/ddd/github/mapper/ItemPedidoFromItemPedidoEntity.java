package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.checkout.entity.ItemPedido;
import diegosneves.ddd.github.infrastructure.order.repository.mysql.ItemPedidoEntity;
import diegosneves.ddd.github.mapper.shared.MapperStrategy;

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
