package diegosneves.ddd.github.exceptions;

public class EventosException extends RuntimeException {

    public static final ManipuladorDeMensagensDeErros ERRO = ManipuladorDeMensagensDeErros.EVENTOS_CAST;
    public EventosException(String message) {
        super(ERRO.mensagem(message));
    }

    public EventosException(String message, Throwable cause) {
        super(ERRO.mensagem(message), cause);
    }
}
