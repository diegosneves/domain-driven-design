package diegosneves.ddd.github.exceptions;

public class ItemException extends RuntimeException {

    public static final ManipuladorDeMensagensDeErros ERRO = ManipuladorDeMensagensDeErros.ITEM_PEDIDO_INVALIDO;
    public ItemException(String message) {
        super(ERRO.mensagem(message));
    }
}
