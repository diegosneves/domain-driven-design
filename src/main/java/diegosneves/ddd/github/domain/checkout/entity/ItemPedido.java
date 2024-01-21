package diegosneves.ddd.github.domain.checkout.entity;

import diegosneves.ddd.github.exceptions.ItemException;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

public class ItemPedido {

    private static final String ID_FIELD_REQUIRED = "O campo ID do item é obrigatório";
    private static final String NAME_FIELD_REQUIRED = "O nome do item é obrigatório";
    private static final String PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO = "O preço do produto deve ser maior que zero";
    private static final String PRODUCT_ID_FIELD_REQUIRED = "É exigida a informação do ID do produto";
    private static final String QUANTITY_MUST_BE_GREATER_THAN_ZERO = "A quantidade deve ser maior que zero";
    private final String id;
    private String produtoId;
    private String name;
    private BigDecimal preco;

    private Integer quantidade;

    public ItemPedido(String id, String name, BigDecimal preco, String produtoId, Integer quantidade) throws ItemException {
        this.id = id;
        this.name = name;
        this.preco = preco;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.validarDados();
    }

    private void validarDados() throws ItemException {
        if (isNull(this.id) || this.id.isBlank()) {
            throw new ItemException(ID_FIELD_REQUIRED);
        }
        if (isNull(this.name) || this.name.isBlank()) {
            throw new ItemException(NAME_FIELD_REQUIRED);
        }
        if (isNull(this.preco) || this.preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ItemException(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO);
        }
        if (isNull(this.produtoId) || this.produtoId.isBlank()) {
            throw new ItemException(PRODUCT_ID_FIELD_REQUIRED);
        }
        if (isNull(this.quantidade) || this.quantidade <= 0) {
            throw new ItemException(QUANTITY_MUST_BE_GREATER_THAN_ZERO);
        }
    }

    public BigDecimal getPreco() {
        return this.preco.multiply(BigDecimal.valueOf(this.quantidade));
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

    public Integer getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", preco=" + this.preco +
                '}';
    }
}
