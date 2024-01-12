package diegosneves.ddd.github.service;

import diegosneves.ddd.github.entity.Cliente;
import diegosneves.ddd.github.entity.ItemPedido;
import diegosneves.ddd.github.entity.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoServiceTest {

    private ItemPedido itemI;
    private ItemPedido itemII;
    private Pedido pedidoI;
    private Pedido pedidoII;
    private Cliente clienteI;

    @BeforeEach
    void setUp() {
        this.itemI = new ItemPedido("I001", "Item I", BigDecimal.valueOf(100.0), "PR01", 1);
        this.itemII = new ItemPedido("I002", "Item II", BigDecimal.valueOf(200.0), "PR02", 2);

        this.pedidoI = new Pedido("P001", "C001", List.of(itemI));
        this.pedidoII = new Pedido("P002", "C002", List.of(itemII));

        this.clienteI = new Cliente("CL001", "Fulano");
    }

    @Test
    void deveCalcularOValorTotalDosPedidos() {
        BigDecimal valorTotal = PedidoService.calcularValorTotalDosPedidos(List.of(this.pedidoI, this.pedidoII));
        assertEquals(BigDecimal.valueOf(500.0), valorTotal);
    }

    @Test
    void deveFazerUmaEncomenda() {
        Pedido pedido = PedidoService.fazerEncomenda(this.clienteI, List.of(this.itemI));

        assertEquals(50, this.clienteI.getPontosDeRecompensa());
        assertEquals(100.0, pedido.calcularCustoTotal().doubleValue());
    }

}
