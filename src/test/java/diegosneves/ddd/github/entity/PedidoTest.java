package diegosneves.ddd.github.entity;

import diegosneves.ddd.github.exceptions.ManipuladorDeMensagensDeErros;
import diegosneves.ddd.github.exceptions.PedidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PedidoTest {

    private static final String ID_INVALIDO = "O ID informado não deve ser nulo nem vazio.";
    private static final String INVALID_CLIENT_CODE = "O código de identificação do cliente não deve ser nulo ou deixado em branco";
    private static final String INVALID_ITEM_LIST = "É obrigatório que a lista de itens do pedido contenha pelo menos um item. A lista não pode ser nula ou vazia.";
    private Pedido pedido;
    private ItemPedido itemPedidoI;
    private ItemPedido itemPedidoII;


    @BeforeEach
    void setUp() {
        this.itemPedidoI = new ItemPedido("P01", "Item I", BigDecimal.TEN);
        this.itemPedidoII = new ItemPedido("P02", "Item II", BigDecimal.ONE);

        this.pedido = new Pedido("001", "C01", List.of(itemPedidoI, itemPedidoII));
    }

    @Test
    void quandoValidarPedidoReceberUmIdNuloEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.pedido.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Method method = this.pedido.getClass().getDeclaredMethod("validarPedido");
        method.setAccessible(true);

        field.set(this.pedido, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.pedido));

        assertInstanceOf(PedidoException.class, exception.getTargetException());
        assertEquals(PedidoException.ERRO.mensagem(ID_INVALIDO), exception.getTargetException().getMessage());

    }

    @Test
    void quandoValidarPedidoReceberUmIdVazioEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.pedido.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Method method = this.pedido.getClass().getDeclaredMethod("validarPedido");
        method.setAccessible(true);

        field.set(this.pedido, " ");

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.pedido));

        assertInstanceOf(PedidoException.class, exception.getTargetException());
        assertEquals(PedidoException.ERRO.mensagem(ID_INVALIDO), exception.getTargetException().getMessage());

    }

    @Test
    void quandoValidarPedidoReceberUmClienteIdNuloEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.pedido.getClass().getDeclaredField("clienteId");
        field.setAccessible(true);
        Method method = this.pedido.getClass().getDeclaredMethod("validarPedido");
        method.setAccessible(true);

        field.set(this.pedido, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.pedido));

        assertInstanceOf(PedidoException.class, exception.getTargetException());
        assertEquals(PedidoException.ERRO.mensagem(INVALID_CLIENT_CODE), exception.getTargetException().getMessage());

    }

    @Test
    void quandoValidarPedidoReceberUmClienteIdVazioEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.pedido.getClass().getDeclaredField("clienteId");
        field.setAccessible(true);
        Method method = this.pedido.getClass().getDeclaredMethod("validarPedido");
        method.setAccessible(true);

        field.set(this.pedido, "");

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.pedido));

        assertInstanceOf(PedidoException.class, exception.getTargetException());
        assertEquals(PedidoException.ERRO.mensagem(INVALID_CLIENT_CODE), exception.getTargetException().getMessage());

    }

    @Test
    void quandoValidarPedidoReceberUmaListaDeItensNulaEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.pedido.getClass().getDeclaredField("itens");
        field.setAccessible(true);
        Method method = this.pedido.getClass().getDeclaredMethod("validarPedido");
        method.setAccessible(true);

        field.set(this.pedido, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.pedido));

        assertInstanceOf(PedidoException.class, exception.getTargetException());
        assertEquals(PedidoException.ERRO.mensagem(INVALID_ITEM_LIST), exception.getTargetException().getMessage());

    }

    @Test
    void quandoValidarPedidoReceberUmaListaDeItensVaziaEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.pedido.getClass().getDeclaredField("itens");
        field.setAccessible(true);
        Method method = this.pedido.getClass().getDeclaredMethod("validarPedido");
        method.setAccessible(true);

        field.set(this.pedido, new ArrayList<>());

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.pedido));

        assertInstanceOf(PedidoException.class, exception.getTargetException());
        assertEquals(PedidoException.ERRO.mensagem(INVALID_ITEM_LIST), exception.getTargetException().getMessage());

    }

    @Test
    void quandoCalcularCustoTotalForChamadoEntaoPrecoDosItensSeraoSomados() {
        BigDecimal expected = BigDecimal.valueOf(11.0);
        BigDecimal actual = pedido.calcularCustoTotal();

        assertEquals(expected, actual);
    }

    @Test
    void quandoToStringForChamadoEntaoOsCamposIdClienteIdAndItensDevemEstarContidosNaString(){
        String actual = pedido.toString();

        assertTrue(actual.contains("001"));
        assertTrue(actual.contains("C01"));
        assertTrue(actual.contains("P01"));
        assertTrue(actual.contains("P02"));
    }


}
