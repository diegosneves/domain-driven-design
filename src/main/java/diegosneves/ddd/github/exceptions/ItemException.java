package diegosneves.ddd.github.exceptions;

public class ItemException extends RuntimeException {

    public static final ManipuladorDeMensagensDeErros ERRO = ManipuladorDeMensagensDeErros.EVENTOS_CAST;
    public ItemException(String message) {
        super(ERRO.mensagem(message));
    }
}
