package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.customer.entity.Cliente;
import diegosneves.ddd.github.domain.customer.entity.value.Endereco;
import diegosneves.ddd.github.infrastructure.customer.repository.mysql.ClienteEntity;
import diegosneves.ddd.github.mapper.shared.MapperStrategy;

import static java.util.Objects.isNull;

public class ClienteFromClienteEntityMapper implements MapperStrategy<Cliente, ClienteEntity> {

    @Override
    public Cliente mapper(ClienteEntity source) {
        Cliente cliente = new Cliente(source.getId(), source.getNome());
        cliente.adicionarPontosDeRecompensa(isNull(source.getPontosDeRecompensa()) ? 0 : source.getPontosDeRecompensa());
        Endereco endereco = new Endereco(source.getRua(), source.getNumero(), source.getCep(), source.getCidade());
        cliente.adicionarEndereco(endereco);
        if (Boolean.TRUE.equals(source.getAtivo())){
            cliente.ativarCliente();
        }
        return cliente;
    }

}
