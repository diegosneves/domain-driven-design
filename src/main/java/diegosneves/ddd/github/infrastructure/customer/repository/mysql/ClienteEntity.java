package diegosneves.ddd.github.infrastructure.customer.repository.mysql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Boolean ativo = Boolean.FALSE;
    @Column(name = "pontos_de_recompensa", nullable = false)
    private Integer pontosDeRecompensa = 0;

    @Column(nullable = false)
    private String rua;
    @Column(nullable = false)
    private Integer numero;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String cidade;

    public ClienteEntity() {
    }

    private ClienteEntity(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.ativo = builder.ativo;
        this.pontosDeRecompensa = builder.pontosDeRecompensa;
        this.rua = builder.rua;
        this.numero = builder.numero;
        this.cep = builder.cep;
        this.cidade = builder.cidade;
    }

    public ClienteEntity(String id, String nome, Boolean ativo, Integer pontosDeRecompensa, String rua, Integer numero, String cep, String cidade) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
        this.pontosDeRecompensa = pontosDeRecompensa;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
    }

    public static class Builder {
        private String id;
        private String nome;
        private Boolean ativo = Boolean.FALSE;
        private Integer pontosDeRecompensa = 0;
        private String rua;
        private Integer numero;
        private String cep;
        private String cidade;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder ativo(Boolean ativo) {
            this.ativo = ativo;
            return this;
        }

        public Builder pontosDeRecompensa(Integer pontosDeRecompensa) {
            this.pontosDeRecompensa = pontosDeRecompensa;
            return this;
        }

        public Builder rua(String rua) {
            this.rua = rua;
            return this;
        }

        public Builder numero(Integer numero) {
            this.numero = numero;
            return this;
        }

        public Builder cep(String cep) {
            this.cep = cep;
            return this;
        }

        public Builder cidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        public void setAtivo(Boolean ativo) {
            this.ativo = ativo;
        }

        public Boolean isAtivo() {
            return ativo;
        }

        public ClienteEntity build() {
            return new ClienteEntity(this);
        }

    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Integer getPontosDeRecompensa() {
        return pontosDeRecompensa;
    }

    public String getRua() {
        return rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "id='" + this.id + '\'' +
                ", nome='" + this.nome + '\'' +
                ", ativo=" + this.ativo +
                ", pontosDeRecompensa=" + this.pontosDeRecompensa +
                ", rua='" + this.rua + '\'' +
                ", numero=" + this.numero +
                ", cep='" + this.cep + '\'' +
                ", cidade='" + this.cidade + '\'' +
                '}';
    }
}
