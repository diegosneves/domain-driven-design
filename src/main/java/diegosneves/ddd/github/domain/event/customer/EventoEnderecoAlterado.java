package diegosneves.ddd.github.domain.event.customer;

import diegosneves.ddd.github.domain.entity.Cliente;
import diegosneves.ddd.github.domain.event.contract.Evento;

public class EventoEnderecoAlterado extends Evento<Cliente> {

    public EventoEnderecoAlterado(Cliente dadosDoEvento) {
        super(dadosDoEvento);
    }
}
