package diegosneves.ddd.github.domain.checkout.entity;

import java.util.List;

public interface PedidoInterface {

    String getId();

    String getClienteId();

    List<ItemPedido> getItens();
}
