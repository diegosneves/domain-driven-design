package diegosneves.ddd.github.domain.product.event;

import diegosneves.ddd.github.domain.product.entity.Produto;
import diegosneves.ddd.github.domain.shared.event.Evento;

public class EventoCriarProduto extends Evento<Produto> {

    public EventoCriarProduto(Produto dadosDoEvento) {
        super(dadosDoEvento);
    }

}
