package diegosneves.ddd.github.entity;

import diegosneves.ddd.github.exceptions.ClienteException;

import static java.util.Objects.isNull;

public class Cliente {

    private static final String INFORMAR_UM_NOME = "Para o registro correto, um nome válido é necessário";
    private static final String INFORMAR_UM_ID = "Um ID válido precisa ser fornecido";
    private static final String ADICIONAR_UM_ENDERECO = "Você deve registrar um endereço para ativar o cliente.";
    private final String id;
    private String nome;
    private Endereco endereco;
    private Boolean ativo = Boolean.FALSE;

    public Cliente(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.validarDados();
    }

    private void validarDados() {
        if (isNull(this.nome) || this.nome.isBlank()) {
            throw new ClienteException(INFORMAR_UM_NOME);
        }
        if (isNull(this.id) || this.id.isBlank()) {
            throw new ClienteException(INFORMAR_UM_ID);
        }
    }

    public void alterarNome(String novoNome) {
        this.nome = novoNome;
        this.validarDados();
    }

    public void ativarCliente() {
        if (isNull(this.endereco)) {
            throw new ClienteException(ADICIONAR_UM_ENDERECO);
        }
        this.ativo = Boolean.TRUE;
    }

    public void desativarCliente() {
        this.ativo = Boolean.FALSE;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
