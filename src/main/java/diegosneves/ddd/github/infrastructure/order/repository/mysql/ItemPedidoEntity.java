package diegosneves.ddd.github.infrastructure.order.repository.mysql;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemPedidoEntity {

    @Id
    private String id;
    @JoinColumn(referencedColumnName = "id", foreignKey = @ForeignKey(foreignKeyDefinition = "ProdutoEntity"))
    @Column(name = "produdo_id", nullable = false)
    private String produtoId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal preco;
    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "itens")
    private PedidoEntity pedido;

    public ItemPedidoEntity() {
    }
    private ItemPedidoEntity(Builder builder) {
        this.id = builder.id;
        this.produtoId = builder.produtoId;
        this.name = builder.name;
        this.preco = builder.preco;
        this.quantidade = builder.quantidade;
        this.pedido = builder.pedido;
    }

    public ItemPedidoEntity(String id, String produtoId, String name, BigDecimal preco, Integer quantidade, PedidoEntity pedido) {
        this.id = id;
        this.produtoId = produtoId;
        this.name = name;
        this.preco = preco;
        this.quantidade = quantidade;
        this.pedido = pedido;
    }

    public static class Builder {
        private String id;
        private String produtoId;
        private String name;
        private BigDecimal preco;
        private Integer quantidade;
        private PedidoEntity pedido;

        public Builder id(String id) {
            this.id = id;
            return this;
        }
        public Builder produtoId(String produtoId) {
            this.produtoId = produtoId;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder preco(BigDecimal preco) {
            this.preco = preco;
            return this;
        }
        public Builder quantidade(Integer quantidade) {
            this.quantidade = quantidade;
            return this;
        }
        public ItemPedidoEntity build() {
            return new ItemPedidoEntity(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getProdutoId() {
        return produtoId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }
}
