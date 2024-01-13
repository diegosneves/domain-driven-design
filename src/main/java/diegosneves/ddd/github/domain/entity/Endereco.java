package diegosneves.ddd.github.domain.entity;

import diegosneves.ddd.github.exceptions.EnderecoException;

import static java.util.Objects.isNull;

public class Endereco {

    private static final String RUA_NOME_AUSENTE = "O nome da rua deve ser informado";
    private static final String NUMERO_RESIDENCIA_OBRIGATORIO = "Um numero de residencia deve ser informado";
    private static final String CEP_REQUIRED = "O CEP deve ser informado";
    private static final String CIDADE_NOME_AUSENTE = "O nome da cidade deve ser informado";
    private String rua;
    private Integer numero;
    private String cep;
    private String cidade;

    public Endereco(String rua, Integer numero, String cep, String cidade) throws EnderecoException {
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.validarDados();
    }

    private void validarDados() throws EnderecoException {
        if (isNull(this.rua) || this.rua.isBlank()) {
            throw new EnderecoException(RUA_NOME_AUSENTE);
        }
        if (isNull(this.numero)) {
            throw new EnderecoException(NUMERO_RESIDENCIA_OBRIGATORIO);
        }
        if (isNull(this.cep) || this.cep.isBlank()) {
            throw new EnderecoException(CEP_REQUIRED);
        }
        if (isNull(this.cidade) || this.cidade.isBlank()) {
            throw new EnderecoException(CIDADE_NOME_AUSENTE);
        }
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", numero=" + numero +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
