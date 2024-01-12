package diegosneves.ddd.github.service;

import diegosneves.ddd.github.entity.Cliente;
import diegosneves.ddd.github.entity.ItemPedido;
import diegosneves.ddd.github.entity.Pedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PedidoService {

    private PedidoService() {
    }

    public static BigDecimal calcularValorTotalDosPedidos(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(Pedido::calcularCustoTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public static Pedido fazerEncomenda(Cliente cliente, List<ItemPedido> items) {
        UUID uuid = UUID.randomUUID();
        Pedido pedido = new Pedido(uuid.toString(), cliente.getId(), items);
        cliente.adicionarPontosDeRecompensa(pedido.calcularCustoTotal().intValue() / 2);
        return pedido;
    }
}
