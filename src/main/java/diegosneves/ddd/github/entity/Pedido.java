package diegosneves.ddd.github.entity;

import java.util.List;

public class Pedido {

    private final String id;
    private String clienteId;
    private List<ItemPedido> itens;

    public Pedido(String id, String clienteId, List<ItemPedido> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", clienteId='" + clienteId + '\'' +
                ", itens=" + itens +
                '}';
    }
}
