package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.entity.ItemPedido;
import diegosneves.ddd.github.domain.entity.Pedido;
import diegosneves.ddd.github.infrastructure.db.mysql.model.ItemPedidoEntity;
import diegosneves.ddd.github.infrastructure.db.mysql.model.PedidoEntity;

public class PedidoFromPedidoEntityMapper implements MapperStrategy<Pedido, PedidoEntity> {

    @Override
    public Pedido mapper(PedidoEntity source) {
        MapperStrategy<ItemPedido, ItemPedidoEntity> mapperStrategy = new ItemPedidoFromItemPedidoEntity();
        return new Pedido(
                source.getId(),
                source.getClienteId(),
                source.getItens().stream()
                        .map(itemPedidoEntity -> BuilderMapper.mapper(ItemPedido.class, itemPedidoEntity, mapperStrategy))
                        .toList());
    }

}
