package diegosneves.ddd.github.domain.product.factory;

import diegosneves.ddd.github.domain.product.entity.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoFactory {

    private ProdutoFactory(){}
    public static Produto criar(String nome, BigDecimal preco) {
        return new Produto(UUID.randomUUID().toString(), nome, preco);
    }

}
