package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.checkout.entity.ItemPedido;
import diegosneves.ddd.github.domain.checkout.entity.Pedido;
import diegosneves.ddd.github.infrastructure.order.repository.mysql.ItemPedidoEntity;
import diegosneves.ddd.github.infrastructure.order.repository.mysql.PedidoEntity;
import diegosneves.ddd.github.mapper.shared.MapperStrategy;

public class PedidoEntityFromPedidoMapper implements MapperStrategy<PedidoEntity, Pedido> {

    @Override
    public PedidoEntity mapper(Pedido source) {
        MapperStrategy<ItemPedidoEntity, ItemPedido> pedidoEntityFromItemPedido = new ItemPedidoEntityFromItemPedido();
        return new PedidoEntity.Builder()
                .id(source.getId())
                .clienteId(source.getClienteId())
                .itens(source.getItens().stream()
                        .map(pedidoEntityFromItemPedido::mapper)
                        .toList())
                .total(source.calcularCustoTotal())
                .build();
    }
}
