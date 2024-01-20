package diegosneves.ddd.github.domain.event.customer;

import diegosneves.ddd.github.domain.entity.Cliente;
import diegosneves.ddd.github.domain.event.contract.Evento;

public class EventoCriarCliente extends Evento<Cliente> {

    public EventoCriarCliente(Cliente dadosDoEvento) {
        super(dadosDoEvento);
    }
}
