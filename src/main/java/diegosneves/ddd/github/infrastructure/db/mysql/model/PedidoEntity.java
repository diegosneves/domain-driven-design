package diegosneves.ddd.github.infrastructure.db.mysql.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    private String id;

    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(foreignKeyDefinition = "ClienteEntity"))
    @Column(name = "cliente_id", nullable = false)
    private String clienteId;

    @Column(nullable = false)
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemPedidoEntity> itens = new ArrayList<>();

    public PedidoEntity(String id, String clienteId, BigDecimal total, List<ItemPedidoEntity> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.total = total;
        this.itens = itens;
    }

    public PedidoEntity() {
    }

    private PedidoEntity(Builder builder) {
        this.id = builder.id;
        this.clienteId = builder.clienteId;
        this.total = builder.total;
        this.itens = builder.itens;
    }

    public static class Builder {
        private String id;
        private String clienteId;
        private BigDecimal total;
        private List<ItemPedidoEntity> itens = new ArrayList<>();

        public Builder id(String id) {
            this.id = id;
            return this;
        }
        public Builder clienteId(String clienteId) {
            this.clienteId = clienteId;
            return this;
        }
        public Builder total(BigDecimal total) {
            this.total = total;
            return this;
        }
        public Builder itens(List<ItemPedidoEntity> itens) {
            this.itens = itens;
            return this;
        }
        public PedidoEntity build() {
            return new PedidoEntity(this);
        }

    }

    public String getId() {
        return this.id;
    }

    public String getClienteId() {
        return this.clienteId;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public List<ItemPedidoEntity> getItens() {
        return this.itens;
    }

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "id='" + this.id + '\'' +
                ", clienteId='" + this.clienteId + '\'' +
                ", total=" + this.total +
                ", itens=" + this.itens +
                '}';
    }
}
