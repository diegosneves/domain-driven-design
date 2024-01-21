package diegosneves.ddd.github.exceptions;

import diegosneves.ddd.github.exceptions.handler.ManipuladorDeMensagensDeErros;

public class EnderecoException extends RuntimeException {

    public static final ManipuladorDeMensagensDeErros ERRO = ManipuladorDeMensagensDeErros.ENDERECO_INVALIDO;
    public EnderecoException(String message) {
        super(ERRO.mensagem(message));
    }
}
