package diegosneves.ddd.github.domain.entity;

import diegosneves.ddd.github.domain.customer.aggregate.Endereco;
import diegosneves.ddd.github.domain.customer.entity.Cliente;
import diegosneves.ddd.github.exceptions.ClienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private static final String INFORMAR_UM_NOME = "Para o registro correto, um nome válido é necessário";
    private static final String INFORMAR_UM_ID = "Um ID válido precisa ser fornecido";
    private static final String ADICIONAR_UM_ENDERECO = "Você deve registrar um endereço para ativar o cliente.";

    private Cliente cliente;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        this.cliente = new Cliente("005", "Diego Neves");
        this.endereco = new Endereco("Rua X", 377, "93", "Sapucaia");
    }


    @Test
    void testAlterarNome() throws Exception {
        String novoNome = "Novo Nome";
        Field field = this.cliente.getClass().getDeclaredField("nome");
        field.setAccessible(true);

        this.cliente.alterarNome(novoNome);

        String nome = (String) field.get(cliente);
        assertEquals(novoNome, nome);
    }

    @Test
    void quandoValidaDadosReceberUmNomeEmBrancoEntaoLancarUmClienteException() throws Exception {
        Field field = this.cliente.getClass().getDeclaredField("nome");
        field.setAccessible(true);
        Method method = this.cliente.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.cliente, "");

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.cliente));

        assertInstanceOf(ClienteException.class, exception.getTargetException());
        assertEquals(ClienteException.ERRO.mensagem(INFORMAR_UM_NOME), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidaDadosReceberUmNomeEmBrancoComEspacosEntaoLancarUmClienteException() throws Exception {
        Field field = this.cliente.getClass().getDeclaredField("nome");
        field.setAccessible(true);
        Method method = this.cliente.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.cliente, "     ");

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.cliente));

        assertInstanceOf(ClienteException.class, exception.getTargetException());
        assertEquals(ClienteException.ERRO.mensagem(INFORMAR_UM_NOME), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidaDadosReceberUmNomeNuloEntaoLancarUmClienteException() throws Exception {
        Field field = this.cliente.getClass().getDeclaredField("nome");
        field.setAccessible(true);
        Method method = this.cliente.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.cliente, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.cliente));

        assertInstanceOf(ClienteException.class, exception.getTargetException());
        assertEquals(ClienteException.ERRO.mensagem(INFORMAR_UM_NOME), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidaDadosReceberUmIDEmBrancoEntaoLancarUmClienteException() throws Exception {
        Field field = this.cliente.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Method method = this.cliente.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.cliente, "");

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.cliente));

        assertInstanceOf(ClienteException.class, exception.getTargetException());
        assertEquals(ClienteException.ERRO.mensagem(INFORMAR_UM_ID), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidaDadosReceberUmIDEmBrancoComEspacosEntaoLancarUmClienteException() throws Exception {
        Field field = this.cliente.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Method method = this.cliente.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.cliente, "     ");

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.cliente));

        assertInstanceOf(ClienteException.class, exception.getTargetException());
        assertEquals(ClienteException.ERRO.mensagem(INFORMAR_UM_ID), exception.getTargetException().getMessage());
    }

    @Test
    void quandoValidaDadosReceberUmIDNuloEntaoLancarUmClienteException() throws Exception {
        Field field = this.cliente.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Method method = this.cliente.getClass().getDeclaredMethod("validarDados");
        method.setAccessible(true);

        field.set(this.cliente, null);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> method.invoke(this.cliente));

        assertInstanceOf(ClienteException.class, exception.getTargetException());
        assertEquals(ClienteException.ERRO.mensagem(INFORMAR_UM_ID), exception.getTargetException().getMessage());
    }

    @Test
    void quandoAtivarClienteVerificarQueEnderecoEstaOKEntaoClienteSeraAtivado() throws Exception {
        Field field = this.cliente.getClass().getDeclaredField("ativo");
        field.setAccessible(true);
        this.cliente.adicionarEndereco(this.endereco);

        this.cliente.ativarCliente();

        assertTrue((boolean) field.get(cliente));
    }

    @Test
    void quandoAtivarClienteVerificarQueEnderecoEstaNuloEntaoClienteNaoSeraAtivadoAndUmClienteExceptionSeraLancada() throws Exception {
        Field field = this.cliente.getClass().getDeclaredField("ativo");
        field.setAccessible(true);

        ClienteException exception = assertThrows(ClienteException.class, () -> this.cliente.ativarCliente());

        assertEquals(ClienteException.ERRO.mensagem(ADICIONAR_UM_ENDERECO), exception.getMessage());
        assertFalse((boolean) field.get(this.cliente));
    }

    @Test
    void quandoDesativarClienteForChamadoEntaoClienteSeraDesativado() throws Exception {
        Field field = this.cliente.getClass().getDeclaredField("ativo");
        field.setAccessible(true);
        this.cliente.adicionarEndereco(this.endereco);

        this.cliente.ativarCliente();
        assertTrue((boolean) field.get(cliente));

        this.cliente.desativarCliente();
        assertFalse((boolean) field.get(this.cliente));
    }

    @Test
    void quandoSetEnderecoForChamadoEntaoUmEnderecoSeraAdicionado() throws Exception {
        Field field = this.cliente.getClass().getDeclaredField("endereco");
        field.setAccessible(true);

        assertNull(field.get(this.cliente));

        this.cliente.adicionarEndereco(this.endereco);
        assertNotNull(field.get(this.cliente));

    }

    @Test
    void quandoAdicionarPontosDeRecompensaEntaoPontosDeRecompensaSeraoAtualizados() throws Exception {
        Integer pontosAntigos = this.cliente.getPontosDeRecompensa();
        Integer pontosAdicionados = 10;

        this.cliente.adicionarPontosDeRecompensa(pontosAdicionados);

        assertEquals(0, pontosAntigos);
        assertEquals(10, this.cliente.getPontosDeRecompensa());
    }

    @Test
    void quandoGetIdEhChamadoParaUmClienteEspecificoEntaoOIdDesseClienteDeveSerRetornado() {
        assertEquals("005", this.cliente.getId());
    }

    @Test
    void deveRetornarStatusAtivacaoCliente() {
        assertEquals(Boolean.FALSE, this.cliente.getAtivo());
    }

    @Test
    void aoChamarToStringDetalhesDoClienteDeveSerRetornado() {
        assertEquals("Cliente{id='005', nome='Diego Neves', endereco=null, ativo=false, pontosDeRecompensa=0}", this.cliente.toString());
    }

    @Test
    void deveRetornarNomeDoCliente() {
        assertEquals("Diego Neves", this.cliente.getNome());
    }

    @Test
    void deveRetornarEnderecoDoCliente() {
        this.cliente.adicionarEndereco(this.endereco);
        assertEquals(this.endereco, this.cliente.getEndereco());
    }

}
