package diegosneves.ddd.github.exceptions;

public class ProdutoException extends RuntimeException {

    public static final ManipuladorDeMensagensDeErros ERRO = ManipuladorDeMensagensDeErros.PRODUTO_INVALIDO;
    public ProdutoException(String message) {
        super(ERRO.mensagem(message));
    }
}
