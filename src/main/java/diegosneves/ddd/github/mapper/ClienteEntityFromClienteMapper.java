package diegosneves.ddd.github.mapper;

import diegosneves.ddd.github.domain.customer.entity.Cliente;
import diegosneves.ddd.github.infrastructure.customer.repository.mysql.ClienteEntity;
import diegosneves.ddd.github.mapper.shared.MapperStrategy;

public class ClienteEntityFromClienteMapper implements MapperStrategy<ClienteEntity, Cliente> {

    @Override
    public ClienteEntity mapper(Cliente source) {
        return new ClienteEntity.Builder()
                .id(source.getId())
                .nome(source.getNome())
                .ativo(source.getAtivo())
                .pontosDeRecompensa(source.getPontosDeRecompensa())
                .rua(source.getEndereco().getRua())
                .numero(source.getEndereco().getNumero())
                .cep(source.getEndereco().getCep())
                .cidade(source.getEndereco().getCidade())
                .build();
    }
}
