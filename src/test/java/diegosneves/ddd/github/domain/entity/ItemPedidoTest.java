package diegosneves.ddd.github.domain.entity;

import diegosneves.ddd.github.domain.checkout.entity.ItemPedido;
import diegosneves.ddd.github.exceptions.ItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemPedidoTest {

    private static final String ID_FIELD_REQUIRED = "O campo ID do item é obrigatório";
    private static final String NAME_FIELD_REQUIRED = "O nome do item é obrigatório";
    private static final String PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO = "O preço do produto deve ser maior que zero";
    private static final String PRODUCT_ID_FIELD_REQUIRED = "É exigida a informação do ID do produto";
    private static final String QUANTITY_MUST_BE_GREATER_THAN_ZERO = "A quantidade deve ser maior que zero";
    private ItemPedido itemPedido;

    @BeforeEach
    void setUp() {
        this.itemPedido = new ItemPedido("IP001", "Item", BigDecimal.TEN, "PR001", 2);
    }

    @Test
    void quandoValidarDadosReceberUmIDNuloEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, null);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(ID_FIELD_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmIDVazioEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, "");
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(ID_FIELD_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmNomeNuloEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("name");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, null);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(NAME_FIELD_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmNomeVazioEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("name");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, "   ");
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(NAME_FIELD_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmPrecoNuloEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("preco");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, null);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmPrecoIgualAZeroEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("preco");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, BigDecimal.ZERO);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmPrecoNegativoEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("preco");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, BigDecimal.valueOf(-1.0));
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmProdutoIDNuloEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("produtoId");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, null);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(PRODUCT_ID_FIELD_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmProdutoIDVazioEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("produtoId");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, "");
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(PRODUCT_ID_FIELD_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmaQuantidadeNulaEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("quantidade");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, null);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(QUANTITY_MUST_BE_GREATER_THAN_ZERO), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmaQuantidadeIgualAZeroEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("quantidade");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, 0);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(QUANTITY_MUST_BE_GREATER_THAN_ZERO), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmaQuantidadeNegativaEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.itemPedido.getClass().getDeclaredField("quantidade");
        field.setAccessible(true);
        Method method = this.itemPedido.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.itemPedido, -1);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.itemPedido));

        assertInstanceOf(ItemException.class, exception.getTargetException());
        assertEquals(ItemException.ERRO.mensagem(QUANTITY_MUST_BE_GREATER_THAN_ZERO), exception.getTargetException().getMessage());
    }

    @Test
    void deveRetornarIdDoItemPedidoAoChamarGetId() {
        assertEquals("IP001", itemPedido.getId());
    }

    @Test
    void deveRetornarProdutoIdDoItemPedidoAoChamarGetProdutoId() {
        assertEquals("PR001", itemPedido.getProdutoId());
    }

    @Test
    void deveRetornarNomeDoItemPedidoAoChamarGetNome() {
        assertEquals("Item", itemPedido.getName());
    }

    @Test
    void deveRetornarPrecoDoItemPedidoAoChamarGetPreco() {
        BigDecimal expected = BigDecimal.valueOf(20);
        assertEquals(expected, itemPedido.getPreco());
    }

    @Test
    void deveRetornarQuantidadeDoItemPedidoAoChamarGetQuantidade() {
        assertEquals(2, itemPedido.getQuantidade());
    }

}
