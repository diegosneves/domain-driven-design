package diegosneves.ddd.github.domain.service;

import diegosneves.ddd.github.domain.product.entity.Produto;
import diegosneves.ddd.github.domain.product.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    private Produto produtoI;
    private Produto produtoII;
    private Produto produtoIII;

    @BeforeEach
    void setUp() {
        this.produtoI = new Produto("PR01", "Produto I", BigDecimal.TEN);
        this.produtoII = new Produto("PR02", "Produto II", BigDecimal.ONE);
        this.produtoIII = new Produto("PR03", "Produto III", BigDecimal.valueOf(20.0));
    }

    @Test
    void deveAumentarOsPrecosDosProdutosQuandoInformadaUmaPorcentagem() {

        ProdutoService.aplicarAumentoPercentualAoPrecoDosProdutos(List.of(this.produtoI, this.produtoII, this.produtoIII), 100.0);

        assertEquals(BigDecimal.valueOf(20.0), this.produtoI.getPreco());
        assertEquals(BigDecimal.valueOf(2.0), this.produtoII.getPreco());
        assertEquals(BigDecimal.valueOf(40.0), this.produtoIII.getPreco());
    }

}
