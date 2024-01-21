package diegosneves.ddd.github.domain.product.service;

import diegosneves.ddd.github.domain.product.entity.Produto;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoService {

    private static final double PERCENTAGE_BASE = 100.0;

    private ProdutoService() {}

    public static void aplicarAumentoPercentualAoPrecoDosProdutos(List<Produto> produtos, Double aumentoPercentual) {
        for (Produto produto : produtos) {
            BigDecimal precoAtualizado = produto.getPreco().add(BigDecimal.valueOf((aumentoPercentual * produto.getPreco().doubleValue()) / PERCENTAGE_BASE));
            produto.alterarPreco(precoAtualizado);
        }
    }

}
