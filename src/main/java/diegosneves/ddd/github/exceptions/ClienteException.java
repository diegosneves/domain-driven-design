package diegosneves.ddd.github.exceptions;

public class ClienteException extends RuntimeException {

    public static final ManipuladorDeMensagensDeErros ERRO = ManipuladorDeMensagensDeErros.CLIENTE_INVALIDO;
    public ClienteException(String message) {
        super(ERRO.mensagem(message));
    }

    public ClienteException(String message, Throwable cause) {
        super(ERRO.mensagem(message), cause);
    }
}
