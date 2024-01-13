package diegosneves.ddd.github.domain.entity;

import diegosneves.ddd.github.exceptions.ProdutoException;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

public class Produto {

    private static final String ID_REQUIRED = "O ID do produto é obrigatório";
    private static final String PRODUCT_NAME_REQUIRED = "Nome do produto é um campo obrigatório e deve ser preenchido";
    private static final String PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO = "O preço do produto deve ser maior que zero";
    private final String id;
    private String nome;
    private BigDecimal preco;

    public Produto(String id, String nome, BigDecimal preco) throws ProdutoException {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.validarDados();
    }

    private void validarDados() throws ProdutoException {
        if (isNull(this.id) || this.id.isBlank()) {
            throw new ProdutoException(ID_REQUIRED);
        }
        if (isNull(this.nome) || this.nome.isBlank()) {
            throw new ProdutoException(PRODUCT_NAME_REQUIRED);
        }
        if (isNull(this.preco) || this.preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProdutoException(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO);
        }
    }

    public void alterarNome(String novoNome) {
        if (isNull(novoNome) || novoNome.isBlank()) {
            throw new ProdutoException(PRODUCT_NAME_REQUIRED);
        }
        this.nome = novoNome;
    }

    public void alterarPreco(BigDecimal novoPreco) {
        if (isNull(novoPreco) || novoPreco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProdutoException(PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO);
        }
        this.preco = novoPreco;
    }

    public BigDecimal getPreco() {
        return this.preco;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
