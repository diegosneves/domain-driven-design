package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.entity.ItemPedido;
import diegosneves.ddd.github.domain.entity.Pedido;
import diegosneves.ddd.github.infrastructure.db.mysql.model.ItemPedidoEntity;
import diegosneves.ddd.github.infrastructure.db.mysql.model.PedidoEntity;

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
