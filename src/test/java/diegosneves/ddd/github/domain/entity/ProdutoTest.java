package diegosneves.ddd.github.domain.entity;

import diegosneves.ddd.github.exceptions.ProdutoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    private static final String ID_REQUIRED = "O ID do produto é obrigatório";
    private static final String PRODUCT_NAME_REQUIRED = "Nome do produto é um campo obrigatório e deve ser preenchido";
    private static final String PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO = "O preço do produto deve ser maior que zero";
    private Produto produto;

    @BeforeEach
    void setUp() {
        this.produto = new Produto("PR01", "Produto", BigDecimal.TEN);
    }

    @Test
    void quandoValidarDadosReceberUmIdNuloEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Method method = this.produto.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.produto, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.produto));

        assertInstanceOf(ProdutoException.class, exception.getTargetException());
        assertEquals(ProdutoException.ERRO.mensagem(ID_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmIdVazioEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Method method = this.produto.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.produto, "");

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.produto));

        assertInstanceOf(ProdutoException.class, exception.getTargetException());
        assertEquals(ProdutoException.ERRO.mensagem(ID_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmNomeNuloEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("nome");
        field.setAccessible(true);
        Method method = this.produto.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.produto, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.produto));

        assertInstanceOf(ProdutoException.class, exception.getTargetException());
        assertEquals(ProdutoException.ERRO.mensagem(PRODUCT_NAME_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmNomeVazioEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("nome");
        field.setAccessible(true);
        Method method = this.produto.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.produto, " ");

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.produto));

        assertInstanceOf(ProdutoException.class, exception.getTargetException());
        assertEquals(ProdutoException.ERRO.mensagem(PRODUCT_NAME_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmPrecoNuloEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("preco");
        field.setAccessible(true);
        Method method = this.produto.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.produto, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.produto));

        assertInstanceOf(ProdutoException.class, exception.getTargetException());
        assertEquals(ProdutoException.ERRO.mensagem(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmPrecoMenorQueZeroEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("preco");
        field.setAccessible(true);
        Method method = this.produto.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.produto, BigDecimal.valueOf(-1.0));

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.produto));

        assertInstanceOf(ProdutoException.class, exception.getTargetException());
        assertEquals(ProdutoException.ERRO.mensagem(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmPrecoIgualAZeroEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("preco");
        field.setAccessible(true);
        Method method = this.produto.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.produto, BigDecimal.ZERO);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.produto));

        assertInstanceOf(ProdutoException.class, exception.getTargetException());
        assertEquals(ProdutoException.ERRO.mensagem(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO), exception.getTargetException().getMessage());
    }

    @Test
    void quandoGetPrecoSerChamadoUmPrecoDeveSerRetornado() {
        assertEquals(BigDecimal.TEN, produto.getPreco());
    }

    @Test
    void quandoAlterarNomeReceberUmNovoNomeValidoEntaoAtributoNomeReceberaNovoValor() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("nome");
        field.setAccessible(true);

        String antigoNome = (String) field.get(this.produto);
        this.produto.alterarNome("Teste");
        String nomeAtualizado = (String) field.get(this.produto);

        assertEquals("Teste", nomeAtualizado);
        assertEquals("Produto", antigoNome);
    }

    @Test
    void quandoAlterarNomeReceberUmNovoNomeInvalidoEntaoAtributoNomeNaoReceberaNovoValor() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("nome");
        field.setAccessible(true);

        String antigoNome = (String) field.get(this.produto);
        ProdutoException exception = assertThrows(ProdutoException.class, () -> this.produto.alterarNome(" "));
        String nomeAtualizado = (String) field.get(this.produto);

        assertEquals(ProdutoException.ERRO.mensagem(PRODUCT_NAME_REQUIRED), exception.getMessage());
        assertEquals("Produto", nomeAtualizado);
        assertEquals("Produto", antigoNome);
    }

    @Test
    void quandoAlterarNomeReceberUmNovoNomeNuloEntaoAtributoNomeNaoReceberaNovoValor() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("nome");
        field.setAccessible(true);

        String antigoNome = (String) field.get(this.produto);
        ProdutoException exception = assertThrows(ProdutoException.class, () -> this.produto.alterarNome(null));
        String nomeAtualizado = (String) field.get(this.produto);

        assertEquals(ProdutoException.ERRO.mensagem(PRODUCT_NAME_REQUIRED), exception.getMessage());
        assertEquals("Produto", nomeAtualizado);
        assertEquals("Produto", antigoNome);
    }

    @Test
    void quandoAlterarPrecoReceberUmNovoPrecoValidoEntaoAtributoPrecoReceberaNovoValor() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("preco");
        field.setAccessible(true);
        BigDecimal precoEsperado = BigDecimal.valueOf(15.0);

        BigDecimal antigoPreco = (BigDecimal) field.get(this.produto);
        this.produto.alterarPreco(BigDecimal.valueOf(15.0));
        BigDecimal precoAtualizado = (BigDecimal) field.get(this.produto);

        assertEquals(precoEsperado, precoAtualizado);
        assertEquals(BigDecimal.TEN, antigoPreco);
    }

    @Test
    void quandoAlterarPrecoReceberUmNovoPrecoInvalidoEntaoAtributoPrecoNaoReceberaNovoValor() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("preco");
        field.setAccessible(true);

        BigDecimal antigoPreco = (BigDecimal) field.get(this.produto);
        ProdutoException exception = assertThrows(ProdutoException.class, () -> this.produto.alterarPreco(BigDecimal.ZERO));
        BigDecimal precoAtualizado = (BigDecimal) field.get(this.produto);

        assertEquals(ProdutoException.ERRO.mensagem(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO), exception.getMessage());
        assertEquals(BigDecimal.TEN, precoAtualizado);
        assertEquals(BigDecimal.TEN, antigoPreco);
    }

    @Test
    void quandoAlterarPrecoReceberUmNovoPrecoNegativoInvalidoEntaoAtributoPrecoNaoReceberaNovoValor() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("preco");
        field.setAccessible(true);

        BigDecimal antigoPreco = (BigDecimal) field.get(this.produto);
        ProdutoException exception = assertThrows(ProdutoException.class, () -> this.produto.alterarPreco(BigDecimal.valueOf(-1.0)));
        BigDecimal precoAtualizado = (BigDecimal) field.get(this.produto);

        assertEquals(ProdutoException.ERRO.mensagem(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO), exception.getMessage());
        assertEquals(BigDecimal.TEN, precoAtualizado);
        assertEquals(BigDecimal.TEN, antigoPreco);
    }

    @Test
    void quandoAlterarPrecoReceberUmNovoPrecoNuloEntaoAtributoPrecoNaoReceberaNovoValor() throws Exception {
        Field field = this.produto.getClass().getDeclaredField("preco");
        field.setAccessible(true);

        BigDecimal antigoPreco = (BigDecimal) field.get(this.produto);
        ProdutoException exception = assertThrows(ProdutoException.class, () -> this.produto.alterarPreco(null));
        BigDecimal precoAtualizado = (BigDecimal) field.get(this.produto);

        assertEquals(ProdutoException.ERRO.mensagem(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO), exception.getMessage());
        assertEquals(BigDecimal.TEN, precoAtualizado);
        assertEquals(BigDecimal.TEN, antigoPreco);
    }

    @Test
    void deveRetornarIdDoProdutoQuandoGetIdChamado() {
        assertEquals("PR01", this.produto.getId());
    }

    @Test
    void deveRetornarNomeDoProdutoQuandoGetNomeForChamado() {
        assertEquals("Produto", this.produto.getNome());
    }

}
