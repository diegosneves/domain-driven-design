package diegosneves.ddd.github;

import diegosneves.ddd.github.domain.customer.entity.Cliente;
import diegosneves.ddd.github.domain.customer.entity.value.Endereco;
import diegosneves.ddd.github.domain.customer.event.handler.EnviarEmailAoCriarClienteHandler;
import diegosneves.ddd.github.domain.customer.event.handler.NotificarAlteracaoEnderecoCliente;
import diegosneves.ddd.github.domain.customer.factory.ClienteFactory;
import diegosneves.ddd.github.domain.customer.repository.ClienteRepository;
import diegosneves.ddd.github.domain.shared.event.DistribuidorEventos;
import diegosneves.ddd.github.infrastructure.customer.repository.mysql.ClienteEntityRepository;

public class App {

    public static void main(String[] args) {
        EnviarEmailAoCriarClienteHandler emailAoCriarClienteHandler = new EnviarEmailAoCriarClienteHandler();
        NotificarAlteracaoEnderecoCliente notificarAlteracaoEnderecoCliente = new NotificarAlteracaoEnderecoCliente();
        DistribuidorEventos.registrar(emailAoCriarClienteHandler);
        DistribuidorEventos.registrar(notificarAlteracaoEnderecoCliente);

        ClienteRepository clienteRepository = new ClienteEntityRepository();
        Endereco endereco = new Endereco("Rua X", 377, "93222222", "Cidade X");
        Cliente cliente = ClienteFactory.criar("Fulano", endereco);
        cliente.adicionarPontosDeRecompensa(20);
        clienteRepository.guardar(cliente);

        Cliente cliente2 = ClienteFactory.criar("Beltrano");
        cliente2.adicionarEndereco(endereco);
        cliente2.adicionarPontosDeRecompensa(20);
        clienteRepository.guardar(cliente2);

    }

}
