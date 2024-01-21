package diegosneves.ddd.github.domain.customer.event;

import diegosneves.ddd.github.domain.customer.entity.Cliente;
import diegosneves.ddd.github.domain.shared.event.Evento;

public class EventoCriarCliente extends Evento<Cliente> {

    public EventoCriarCliente(Cliente dadosDoEvento) {
        super(dadosDoEvento);
    }
}
