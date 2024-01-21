package diegosneves.ddd.github.exceptions;

import diegosneves.ddd.github.exceptions.handler.ManipuladorDeMensagensDeErros;

public class ProdutoException extends RuntimeException {

    public static final ManipuladorDeMensagensDeErros ERRO = ManipuladorDeMensagensDeErros.PRODUTO_INVALIDO;
    public ProdutoException(String message) {
        super(ERRO.mensagem(message));
    }

    public ProdutoException(String message, Throwable cause) {
        super(ERRO.mensagem(message), cause);
    }
}
