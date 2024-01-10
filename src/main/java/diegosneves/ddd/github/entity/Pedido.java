package diegosneves.ddd.github.entity;

import diegosneves.ddd.github.exceptions.PedidoException;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Objects.isNull;

public class Pedido {

    private static final String ID_INVALIDO = "O ID informado não deve ser nulo nem vazio.";
    private static final String INVALID_CLIENT_CODE = "O código de identificação do cliente não deve ser nulo ou deixado em branco";
    private static final String INVALID_ITEM_LIST = "É obrigatório que a lista de itens do pedido contenha pelo menos um item. A lista não pode ser nula ou vazia.";

    private final String id;
    private String clienteId;
    private List<ItemPedido> itens;
    private BigDecimal total;

    public Pedido(String id, String clienteId, List<ItemPedido> itens) throws PedidoException {
        this.id = id;
        this.clienteId = clienteId;
        this.itens = itens;
        this.total = BigDecimal.ZERO;
        this.validarPedido();
    }

    private void validarPedido() throws PedidoException {
        if (isNull(this.id) || this.id.isBlank()) {
            throw new PedidoException(ID_INVALIDO);
        }
        if (isNull(this.clienteId) || this.clienteId.isBlank()) {
            throw new PedidoException(INVALID_CLIENT_CODE);
        }
        if (isNull(this.itens) || this.itens.isEmpty()) {
            throw new PedidoException(INVALID_ITEM_LIST);
        }
    }

    public BigDecimal calcularCustoTotal() {
        this.total = BigDecimal.valueOf(this.itens.stream().mapToDouble(i -> i.getPreco().doubleValue()).sum());
        return this.total;
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
