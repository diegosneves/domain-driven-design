package diegosneves.ddd.github.domain.customer.event;

import diegosneves.ddd.github.domain.customer.entity.Cliente;
import diegosneves.ddd.github.domain.shared.event.Evento;

public class EventoEnderecoAlterado extends Evento<Cliente> {

    public EventoEnderecoAlterado(Cliente dadosDoEvento) {
        super(dadosDoEvento);
    }
}
