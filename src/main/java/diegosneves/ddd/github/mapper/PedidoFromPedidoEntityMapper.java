package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.checkout.entity.ItemPedido;
import diegosneves.ddd.github.domain.checkout.entity.Pedido;
import diegosneves.ddd.github.infrastructure.order.repository.mysql.ItemPedidoEntity;
import diegosneves.ddd.github.infrastructure.order.repository.mysql.PedidoEntity;
import diegosneves.ddd.github.mapper.shared.BuilderMapper;
import diegosneves.ddd.github.mapper.shared.MapperStrategy;

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
