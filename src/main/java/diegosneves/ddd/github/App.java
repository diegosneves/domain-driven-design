package diegosneves.ddd.github;

import diegosneves.ddd.github.domain.entity.Cliente;
import diegosneves.ddd.github.domain.entity.Endereco;
import diegosneves.ddd.github.domain.entity.ItemPedido;
import diegosneves.ddd.github.domain.entity.Pedido;

import java.math.BigDecimal;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Cliente cliente = new Cliente("005", "Diego Neves");
        Endereco endereco = new Endereco("Rua X", 377, "93210220", "Sapucaia do Sul");
        cliente.setEndereco(endereco);
        cliente.ativarCliente();

        ItemPedido item1 = new ItemPedido("001", "Item 1", BigDecimal.valueOf(10.0), "PR01", 2);
        ItemPedido item2 = new ItemPedido("002", "Item 2", BigDecimal.valueOf(15.0), "PR02", 2);
        Pedido pedido = new Pedido("P01", "005", List.of(item1, item2));
        System.out.println(pedido);

//        ProdutoRepository repository = new ProdutoEntityRepository();
//        Produto produto =new Produto("PR02", "Produto II", BigDecimal.valueOf(200.0));
//        repository.guardar(produto);
    }

}
