package diegosneves.ddd.github.domain.entity;

import diegosneves.ddd.github.domain.customer.aggregate.Endereco;
import diegosneves.ddd.github.exceptions.EnderecoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    private static final String RUA_NOME_AUSENTE = "O nome da rua deve ser informado";
    private static final String NUMERO_RESIDENCIA_OBRIGATORIO = "Um numero de residencia deve ser informado";
    private static final String CEP_REQUIRED = "O CEP deve ser informado";
    private static final String CIDADE_NOME_AUSENTE = "O nome da cidade deve ser informado";

    private Endereco endereco;

    @BeforeEach
    void setUp() {
        this.endereco = new Endereco("Rua A", 337, "00000000", "Cidade");
    }

    @Test
    void quandoValidarDadosReceberUmaRuaNulaEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.endereco.getClass().getDeclaredField("rua");
        field.setAccessible(true);
        Method method = this.endereco.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.endereco, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.endereco));

        assertInstanceOf(EnderecoException.class, exception.getTargetException());
        assertEquals(EnderecoException.ERRO.mensagem(RUA_NOME_AUSENTE), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmaRuaVaziaEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.endereco.getClass().getDeclaredField("rua");
        field.setAccessible(true);
        Method method = this.endereco.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.endereco, "");

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.endereco));

        assertInstanceOf(EnderecoException.class, exception.getTargetException());
        assertEquals(EnderecoException.ERRO.mensagem(RUA_NOME_AUSENTE), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmNumeroNuloEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.endereco.getClass().getDeclaredField("numero");
        field.setAccessible(true);
        Method method = this.endereco.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.endereco, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.endereco));

        assertInstanceOf(EnderecoException.class, exception.getTargetException());
        assertEquals(EnderecoException.ERRO.mensagem(NUMERO_RESIDENCIA_OBRIGATORIO), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmCepNuloEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.endereco.getClass().getDeclaredField("cep");
        field.setAccessible(true);
        Method method = this.endereco.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.endereco, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.endereco));

        assertInstanceOf(EnderecoException.class, exception.getTargetException());
        assertEquals(EnderecoException.ERRO.mensagem(CEP_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmCepVazioEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.endereco.getClass().getDeclaredField("cep");
        field.setAccessible(true);
        Method method = this.endereco.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.endereco, " ");

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.endereco));

        assertInstanceOf(EnderecoException.class, exception.getTargetException());
        assertEquals(EnderecoException.ERRO.mensagem(CEP_REQUIRED), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmaCidadeNulaEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.endereco.getClass().getDeclaredField("cidade");
        field.setAccessible(true);
        Method method = this.endereco.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.endereco, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.endereco));

        assertInstanceOf(EnderecoException.class, exception.getTargetException());
        assertEquals(EnderecoException.ERRO.mensagem(CIDADE_NOME_AUSENTE), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidarDadosReceberUmaCidadeVaziaEntaoUmaExceptionDeveSerLancada() throws Exception {
        Field field = this.endereco.getClass().getDeclaredField("cidade");
        field.setAccessible(true);
        Method method = this.endereco.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.endereco, "");

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.endereco));

        assertInstanceOf(EnderecoException.class, exception.getTargetException());
        assertEquals(EnderecoException.ERRO.mensagem(CIDADE_NOME_AUSENTE), exception.getTargetException().getMessage());
    }

    @Test
    void quandoToStringForChamadaEntaoUmaStringContendoRuaNumeroCepCidadeDeveSerRetornada() {
        String result = this.endereco.toString();

        assertTrue(result.contains("Rua A"));
        assertTrue(result.contains("337"));
        assertTrue(result.contains("00000000"));
        assertTrue(result.contains("Cidade"));
    }

    @Test
    void deveRetornarValorEsperadoParaNomeDaRuaAoChamarGetRua() {
        assertEquals("Rua A", endereco.getRua());
    }

    @Test
    void deveRetornarValorEsperadoParaNumeroDaResidenciaAoChamarGetNumero() {
        assertEquals(337, endereco.getNumero());
    }

    @Test
    void deveRetornarValorEsperadoParaCepAoChamarGetCep() {
        assertEquals("00000000", endereco.getCep());
    }

    @Test
    void deveRetornarValorEsperadoParaNomeDaCidadeAoChamarGetCidade() {
        assertEquals("Cidade", endereco.getCidade());
    }

}
