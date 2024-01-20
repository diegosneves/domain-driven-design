package diegosneves.ddd.github.domain.event.customer.handler;

import diegosneves.ddd.github.domain.entity.Cliente;
import diegosneves.ddd.github.domain.event.contract.ManipuladorEventoContrato;
import diegosneves.ddd.github.domain.event.customer.EventoCriarCliente;

public class EnviarEmailAoCriarClienteHandler implements ManipuladorEventoContrato<EventoCriarCliente> {

    @Override
    public String eventName() {
        return EventoCriarCliente.class.getSimpleName();
    }

    @Override
    public void processarEvento(EventoCriarCliente evento) {
        Cliente cliente = evento.getDadosDoEvento();
        System.out.printf("%s criado às [%s]\nID: %s\nNome do Cliente: %s\nPontos de Recompensa: %02d pontos\nCliente Ativo: %b\nEndereço: %s\nEnviando email...\n",
                evento.getObjectName(),
                evento.getData(),
                cliente.getId(),
                cliente.getNome(),
                cliente.getPontosDeRecompensa(),
                cliente.getAtivo(),
                cliente.getEndereco());
    }
}
