package diegosneves.ddd.github.exceptions;

public class EnderecoException extends RuntimeException {

    public static final ManipuladorDeMensagensDeErros ERRO = ManipuladorDeMensagensDeErros.ENDERECO_INVALIDO;
    public EnderecoException(String message) {
        super(ERRO.mensagem(message));
    }
}
