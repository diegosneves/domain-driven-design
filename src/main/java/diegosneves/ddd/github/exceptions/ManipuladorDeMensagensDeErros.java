package diegosneves.ddd.github.exceptions;

public enum ManipuladorDeMensagensDeErros {

    CLIENTE_INVALIDO("Não conseguimos criar o cliente: [ %s ]"),
    ENDERECO_INVALIDO("Houve um problema ao criar o endereço: [ %s ]");


    private final String erro;
    ManipuladorDeMensagensDeErros(String erro) {
        this.erro = erro;
    }

    public String mensagem(String motivo) {
        return String.format(this.erro, motivo);
    }

}
