package diegosneves.ddd.github.domain.event.product.handler;

import diegosneves.ddd.github.domain.entity.Produto;
import diegosneves.ddd.github.domain.event.contract.ManipuladorEventoContrato;
import diegosneves.ddd.github.domain.event.product.EventoCriarProduto;

public class EnviarEmailAoCriarProdutoHandler implements ManipuladorEventoContrato<EventoCriarProduto> {

    @Override
    public String eventName() {
        return EventoCriarProduto.class.getSimpleName();
    }

    @Override
    public void processarEvento(EventoCriarProduto evento) {
        Produto produto = evento.getDadosDoEvento();
        System.out.printf("%s Criado - [ %s ]\nID: %s\nNome do Produto: %s\nPreço: R$%,.2f\nEnviando email...\n",
                evento.getObjectName(),
                evento.getData(),
                produto.getId(),
                produto.getNome(),
                produto.getPreco().doubleValue());
    }

}
