package diegosneves.ddd.github.domain.checkout.factory;

import diegosneves.ddd.github.domain.checkout.entity.ItemPedido;
import diegosneves.ddd.github.domain.checkout.entity.Pedido;

import java.util.List;
import java.util.UUID;

public class PedidoFactory {

    private PedidoFactory(){}

    public static Pedido criar(String clienteId, List<ItemPedido> itens) {
        return new Pedido(UUID.randomUUID().toString(), clienteId, itens);
    }

}
