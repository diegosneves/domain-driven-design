package diegosneves.ddd.github.entity;

import java.math.BigDecimal;

public class ItemPedido {

    private final String id;
    private String name;
    private BigDecimal preco;

    public ItemPedido(String id, String name, BigDecimal preco) {
        this.id = id;
        this.name = name;
        this.preco = preco;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", preco=" + preco +
                '}';
    }
}
