package diegosneves.ddd.github.domain.event.product.handler;

import diegosneves.ddd.github.domain.event.contract.Evento;
import diegosneves.ddd.github.domain.event.contract.ManipuladorEventoContrato;
import diegosneves.ddd.github.domain.event.product.EventoCriarProduto;

public class EnviarEmailAoCriarProdutoHandler implements ManipuladorEventoContrato<EventoCriarProduto> {

    @Override
    public String eventName() {
        return EventoCriarProduto.class.getSimpleName();
    }

    @Override
    public void processarEvento(Evento evento) {
        System.out.printf("Enviando email... %s Criado!! [ %s ]", evento.getDadosDoEvento().getClass().getSimpleName(), evento.getData());
    }

}
