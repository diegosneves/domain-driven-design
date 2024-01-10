package diegosneves.ddd.github.exceptions;

public class PedidoException extends RuntimeException {

    public static final ManipuladorDeMensagensDeErros ERRO = ManipuladorDeMensagensDeErros.PEDIDO_INVALIDO;
    public PedidoException(String message) {
        super(ERRO.mensagem(message));
    }
}
