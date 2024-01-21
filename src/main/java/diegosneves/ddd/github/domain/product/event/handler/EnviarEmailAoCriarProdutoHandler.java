package diegosneves.ddd.github.domain.product.event.handler;

import diegosneves.ddd.github.domain.product.entity.Produto;
import diegosneves.ddd.github.domain.shared.event.ManipuladorEventoContrato;
import diegosneves.ddd.github.domain.product.event.EventoCriarProduto;

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
