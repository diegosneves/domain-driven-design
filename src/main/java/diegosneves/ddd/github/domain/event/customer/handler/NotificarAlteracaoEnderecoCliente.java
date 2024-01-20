package diegosneves.ddd.github.domain.event.customer.handler;

import diegosneves.ddd.github.domain.entity.Cliente;
import diegosneves.ddd.github.domain.event.contract.ManipuladorEventoContrato;
import diegosneves.ddd.github.domain.event.customer.EventoEnderecoAlterado;

public class NotificarAlteracaoEnderecoCliente implements ManipuladorEventoContrato<EventoEnderecoAlterado> {

    @Override
    public String eventName() {
        return EventoEnderecoAlterado.class.getSimpleName();
    }

    @Override
    public void processarEvento(EventoEnderecoAlterado evento) {
        Cliente cliente = evento.getDadosDoEvento();
        System.out.printf("Endereço do %s\nID: %s\nNome: %s\nFoi alterado às [%s] para:\n> %s\n",
                evento.getObjectName(), cliente.getId(), cliente.getNome(), evento.getData(), cliente.getEndereco());
    }
}
