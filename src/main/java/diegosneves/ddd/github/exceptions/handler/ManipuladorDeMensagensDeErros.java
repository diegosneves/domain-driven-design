package diegosneves.ddd.github.exceptions.handler;

public enum ManipuladorDeMensagensDeErros {

    CLIENTE_INVALIDO("Não conseguimos criar o cliente: [ %s ]"),
    PEDIDO_INVALIDO("Não conseguimos criar um pedido: [ %s ]"),
    PRODUTO_INVALIDO("Não conseguimos criar um produto: [ %s ]"),
    ITEM_PEDIDO_INVALIDO("Não conseguimos criar um item: [ %s ]"),
    EVENTOS_CAST("Não foi possivel para o cast para essa classe: [ %s ]"),
    ENDERECO_INVALIDO("Houve um problema ao criar o endereço: [ %s ]");


    private final String erro;
    ManipuladorDeMensagensDeErros(String erro) {
        this.erro = erro;
    }

    public String mensagem(String motivo) {
        return String.format(this.erro, motivo);
    }

}
