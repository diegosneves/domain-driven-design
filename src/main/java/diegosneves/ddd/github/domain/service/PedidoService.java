package diegosneves.ddd.github.domain.service;

import diegosneves.ddd.github.domain.entity.Cliente;
import diegosneves.ddd.github.domain.entity.ItemPedido;
import diegosneves.ddd.github.domain.entity.Pedido;
import diegosneves.ddd.github.exceptions.PedidoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

public class PedidoService {

    private static final String CLIENTE_OBRIGATORIO = "Você precisa informar quem é o cliente para realizar o pedido.";

    private PedidoService() {
    }

    public static BigDecimal calcularValorTotalDosPedidos(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(Pedido::calcularCustoTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public static Pedido fazerEncomenda(Cliente cliente, List<ItemPedido> items) throws PedidoException {
        if (isNull(cliente)) {
            throw new PedidoException(CLIENTE_OBRIGATORIO);
        }
        UUID uuid = UUID.randomUUID();
        Pedido pedido = new Pedido(uuid.toString(), cliente.getId(), items);
        cliente.adicionarPontosDeRecompensa(pedido.calcularCustoTotal().intValue() / 2);
        return pedido;
    }
}
