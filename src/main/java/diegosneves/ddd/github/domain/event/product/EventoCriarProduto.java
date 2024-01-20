package diegosneves.ddd.github.domain.event.product;

import diegosneves.ddd.github.domain.entity.Produto;
import diegosneves.ddd.github.domain.event.contract.Evento;

public class EventoCriarProduto extends Evento<Produto> {

    public EventoCriarProduto(Produto dadosDoEvento) {
        super(dadosDoEvento);
    }

}
