package diegosneves.ddd.github.domain.entity;

import diegosneves.ddd.github.domain.event.DistribuidorEventos;
import diegosneves.ddd.github.domain.event.customer.EventoEnderecoAlterado;
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
    private Integer pontosDeRecompensa = 0;

    public Cliente(String id, String nome) throws ClienteException {
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

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Integer getPontosDeRecompensa() {
        return this.pontosDeRecompensa;
    }

    public void adicionarPontosDeRecompensa(Integer pontos) {
        this.pontosDeRecompensa += pontos;
    }

    public void desativarCliente() {
        this.ativo = Boolean.FALSE;
    }

    public void adicionarEndereco(Endereco endereco) {
        this.endereco = endereco;
        DistribuidorEventos.notificar(new EventoEnderecoAlterado(this));
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + this.id + '\'' +
                ", nome='" + this.nome + '\'' +
                ", endereco=" + this.endereco +
                ", ativo=" + this.ativo +
                ", pontosDeRecompensa=" + this.pontosDeRecompensa +
                '}';
    }
}
