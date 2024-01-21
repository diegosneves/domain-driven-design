package diegosneves.ddd.github.domain.customer.entity;

import diegosneves.ddd.github.domain.customer.entity.value.Endereco;

public interface ClienteInterface {

    String getId();

    String getNome();

    Endereco getEndereco();

    Boolean getAtivo();

    Integer getPontosDeRecompensa();

}
