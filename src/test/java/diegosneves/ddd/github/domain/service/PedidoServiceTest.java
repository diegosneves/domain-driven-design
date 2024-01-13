package diegosneves.ddd.github.domain.service;

import diegosneves.ddd.github.domain.entity.Cliente;
import diegosneves.ddd.github.domain.entity.ItemPedido;
import diegosneves.ddd.github.domain.entity.Pedido;
import diegosneves.ddd.github.exceptions.PedidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PedidoServiceTest {

    private static final String INVALID_ITEM_LIST = "É obrigatório que a lista de itens do pedido contenha pelo menos um item. A lista não pode ser nula ou vazia.";
    private static final String CLIENTE_OBRIGATORIO = "Você precisa informar quem é o cliente para realizar o pedido.";

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
    void deveCriarPedidoEAtribuirPontosDeRecompensaAoCliente() {
        Pedido pedido = PedidoService.fazerEncomenda(this.clienteI, List.of(this.itemI));

        assertEquals(50, this.clienteI.getPontosDeRecompensa());
        assertEquals(100.0, pedido.calcularCustoTotal().doubleValue());
    }

    @Test
    void deveFalharAoFazerEncomendaComListaDeItensNula() {

        PedidoException exception = assertThrows(PedidoException.class, () -> PedidoService.fazerEncomenda(this.clienteI, null));

        assertEquals(PedidoException.ERRO.mensagem(INVALID_ITEM_LIST), exception.getMessage());
    }

    @Test
    void deveFalharAoFazerEncomendaComListaDeItensVazia() {

        PedidoException exception = assertThrows(PedidoException.class, () -> PedidoService.fazerEncomenda(this.clienteI, new ArrayList<>()));

        assertEquals(PedidoException.ERRO.mensagem(INVALID_ITEM_LIST), exception.getMessage());
    }

    @Test
    void deveFalharAoFazerEncomendaComUmClienteNulo() {

        PedidoException exception = assertThrows(PedidoException.class, () -> PedidoService.fazerEncomenda(null, List.of(this.itemI)));

        assertEquals(PedidoException.ERRO.mensagem(CLIENTE_OBRIGATORIO), exception.getMessage());
    }


}
