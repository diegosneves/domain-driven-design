package diegosneves.ddd.github.exceptions;

import diegosneves.ddd.github.exceptions.handler.ManipuladorDeMensagensDeErros;

public class PedidoException extends RuntimeException {

    public static final ManipuladorDeMensagensDeErros ERRO = ManipuladorDeMensagensDeErros.PEDIDO_INVALIDO;
    public PedidoException(String message) {
        super(ERRO.mensagem(message));
    }
    public PedidoException(String message, Throwable cause) {
        super(ERRO.mensagem(message), cause);
    }
}
