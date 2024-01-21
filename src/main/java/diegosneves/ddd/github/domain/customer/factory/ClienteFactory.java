package diegosneves.ddd.github.domain.customer.factory;

import diegosneves.ddd.github.domain.customer.entity.Cliente;
import diegosneves.ddd.github.domain.customer.entity.value.Endereco;

import java.util.UUID;

public class ClienteFactory {

    private ClienteFactory(){}

    public static Cliente criar(String nome) {
        return new Cliente(UUID.randomUUID().toString(), nome);
    }

    public static Cliente criar(String nome, Endereco endereco) {
        Cliente cliente = new Cliente(UUID.randomUUID().toString(), nome);
        cliente.adicionarEndereco(endereco);
        cliente.ativarCliente();
        return cliente;
    }

}
