package diegosneves.ddd.github.domain.event.product;

import diegosneves.ddd.github.domain.event.contract.Evento;

public class EventoCriarProduto extends Evento {

    public EventoCriarProduto(Object dadosDoEvento) {
        super(dadosDoEvento);
    }

}
