package diegosneves.ddd.github;

import diegosneves.ddd.github.domain.entity.*;
import diegosneves.ddd.github.domain.repository.ClienteRepository;
import diegosneves.ddd.github.domain.repository.ProdutoRepository;
import diegosneves.ddd.github.repository.ClienteEntityRepository;
import diegosneves.ddd.github.repository.PedidoEntityRepository;
import diegosneves.ddd.github.repository.ProdutoEntityRepository;

import java.math.BigDecimal;
import java.util.List;

public class App {

    public static void main(String[] args) {
        PedidoEntityRepository pedidoEntityRepository = new PedidoEntityRepository();
//        ClienteRepository clienteRepository = new ClienteEntityRepository();
//        String id = "002";
//        Cliente cliente = new Cliente(id, "Fulano");
//        Endereco endereco = new Endereco("Rua X", 377, "93222222", "Cidade X");
//        cliente.adicionarEndereco(endereco);
//        cliente.ativarCliente();
//        cliente.adicionarPontosDeRecompensa(20);
        ItemPedido item1 = new ItemPedido("001", "Item 1", BigDecimal.valueOf(10.0), "PR01", 2);
        ItemPedido item2 = new ItemPedido("002", "Item 2", BigDecimal.valueOf(15.0), "PR02", 2);

        Pedido pedido = new Pedido("P01", "001", List.of(item1, item2));

//        pedidoEntityRepository.guardar(pedido);

        Pedido retorno = pedidoEntityRepository.buscarPorId("P01");
//        pedidoEntityRepository.atualizar(pedido);
        System.out.println(retorno);
        System.out.println(pedido);
//        clienteRepository.guardar(cliente);
//        Cliente foundClient = clienteRepository.buscarPorId(id);
//        foundClient.adicionarEndereco(endereco);
//        foundClient.adicionarPontosDeRecompensa(30);
//        clienteRepository.atualizar(foundClient);
//        clienteRepository.deletar(foundClient);
//        List<Cliente> buscarTodos = clienteRepository.buscarTodos();
//        buscarTodos.forEach(System.out::println);

//        System.out.println(foundClient);
//        System.out.println(cliente);
//
//        Pedido pedido = new Pedido("P01", "005", List.of(item1, item2));
//        System.out.println(pedido);

//        String id = "PR04";
//        ProdutoRepository repository = new ProdutoEntityRepository();
//        Produto produto = new Produto(id, "Produto IV", BigDecimal.valueOf(400.0));

//        repository.guardar(produto);
//        repository.atualizar(produto);
//        repository.deletar(produto);
//        Produto produto = repository.buscarPorId(id);
//        List<Produto> allProducts = repository.buscarTodos();

//        System.out.println(produto);
//        allProducts.forEach(System.out::println);
    }

}
