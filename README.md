# Domain-Driven Design (DDD) 
[![Linkedin badge](https://img.shields.io/badge/-Linkedin-blue?flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/diego-neves-224208177/)](https://www.linkedin.com/in/diego-neves-224208177/)
[![wakatime](https://wakatime.com/badge/user/018bea20-dbbc-48e2-b101-5415903acf5a/project/018cdb7e-7b9a-450a-bb76-ac938c307675.svg)](https://wakatime.com/@diegosneves)

Domain-Driven Design (DDD) é uma metodologia aplicada no desenvolvimento de software que prima pela incorporação e centralização do conhecimento de domínio durante a construção de sistemas complexos. 
Este conceito foi difundido principalmente por meio do livro de **Eric Evans**, ["Domain-Driven Design: Tackling Complexity in the Heart of Software"](https://www.amazon.com/Domain-Driven-Design-Tackling-Complexity-Software/dp/0321125215).

Na busca pelo entendimento de DDD, deparar-se-á com alguns conceitos chaves:

- **Modelo de Domínio**: Representa a totalidade da problemática à qual o software propõe-se resolver, em forma de um sistema estruturado e elucidativo.

- **Linguagem Ubíqua**: Uma linguagem desenvolvida em comum acordo e compreensão entre desenvolvedores e usuários a fim de comunicar-melhor a complexidade do problema e minimizar mal-entendidos.

- **Contextos Delimitados**: A forma de decompor sistemas grandes e complexos em sub-sistemas ou 'contextos delimitados' menores e de mais fácil gerenciamento. Cada contexto possui seu próprio modelo de domínio, o qual só possui validade dentro do seu respectivo contexto.

- **Entidades, Objetos de Valor e Agregados**: DDD propõe padrões específicos para lidar com objetos de domínio, tais como Entidades (objetos com uma identidade única), Objetos de Valor (objetos definidos exclusivamente por seus atributos) e Agregados (conjunto de objetos tratados como uma unidade).

- **Serviços de Domínio**: São operações conceituais dentro do domínio que não estão associadas diretamente a uma Entidade ou Objeto de Valor.

- **Fábricas**: São responsáveis pela encapsulação da lógica de instanciar objetos complexos.

- **Repositórios**: Coleções de agregados ou entidades, geralmente utilizados para obter acesso persistente.

Vale ressaltar que este é somente um panorama superficial de DDD. A metodologia é vasta e aprofundada e é altamente recomendado um estudo mais minucioso para quem desejar incorporá-la ao seu processo de desenvolvimento.

---

```mermaid
classDiagram
direction BT
class App { 
  + App() 
}
class Cliente {
  + Cliente(String, String) 
  - Endereco endereco
  - Integer pontosDeRecompensa
  - String ADICIONAR_UM_ENDERECO
  - Boolean ativo
  - String nome
  - String id
  - String INFORMAR_UM_ID
  - String INFORMAR_UM_NOME
  - validarDados() void
  + adicionarPontosDeRecompensa(Integer) void
  + desativarCliente() void
  + getId() String
  + getPontosDeRecompensa() Integer
  + alterarNome(String) void
  + setEndereco(Endereco) void
  + ativarCliente() void
}
class ClienteException {
  + ClienteException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class Endereco {
  + Endereco(String, Integer, String, String) 
  - String CEP_REQUIRED
  - String NUMERO_RESIDENCIA_OBRIGATORIO
  - Integer numero
  - String RUA_NOME_AUSENTE
  - String CIDADE_NOME_AUSENTE
  - String cep
  - String rua
  - String cidade
  - validarDados() void
  + toString() String
}
class EnderecoException {
  + EnderecoException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class ItemException {
  + ItemException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class ItemPedido {
  + ItemPedido(String, String, BigDecimal, String, Integer) 
  - String PRODUCT_ID_FIELD_REQUIRED
  - String QUANTITY_MUST_BE_GREATER_THAN_ZERO
  - BigDecimal preco
  - String NAME_FIELD_REQUIRED
  - String name
  - String ID_FIELD_REQUIRED
  - String id
  - String PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO
  - String produtoId
  - Integer quantidade
  - validarDados() void
  + getPreco() BigDecimal
  + toString() String
}
class ManipuladorDeMensagensDeErros {
<<enumeration>>
  - ManipuladorDeMensagensDeErros(String) 
  +  ITEM_PEDIDO_INVALIDO
  - String erro
  +  PRODUTO_INVALIDO
  +  ENDERECO_INVALIDO
  +  CLIENTE_INVALIDO
  +  PEDIDO_INVALIDO
  + mensagem(String) String
}
class Pedido {
  + Pedido(String, String, List~ItemPedido~) 
  - String INVALID_CLIENT_CODE
  - String clienteId
  - String ID_INVALIDO
  - String INVALID_ITEM_LIST
  - String id
  - BigDecimal total
  - List~ItemPedido~ itens
  + toString() String
  - validarPedido() void
  + calcularCustoTotal() BigDecimal
}
class PedidoException {
  + PedidoException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class PedidoService {
  - PedidoService() 
  - String CLIENTE_OBRIGATORIO
  + fazerEncomenda(Cliente, List~ItemPedido~) Pedido
  + calcularValorTotalDosPedidos(List~Pedido~) BigDecimal
}
class Produto {
  + Produto(String, String, BigDecimal) 
  - String ID_REQUIRED
  - String PRODUCT_NAME_REQUIRED
  - String id
  - BigDecimal preco
  - String PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO
  - String nome
  + alterarNome(String) void
  + alterarPreco(BigDecimal) void
  + getPreco() BigDecimal
  - validarDados() void
}
class ProdutoException {
  + ProdutoException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class ProdutoService {
  - ProdutoService() 
  - double PERCENTAGE_BASE
  + aplicarAumentoPercentualAoPrecoDosProdutos(List~Produto~, Double) void
}

App  ..>  Cliente 
App  ..>  Endereco 
App  ..>  ItemPedido 
App  ..>  Pedido 
Cliente  ..>  ClienteException 
Cliente "1" *--> "endereco 1" Endereco 
ClienteException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
Endereco  ..>  EnderecoException 
EnderecoException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
ItemException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
ItemPedido  ..>  ItemException 
Pedido "1" *--> "itens *" ItemPedido 
Pedido  ..>  PedidoException 
PedidoException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
PedidoService  ..>  Cliente 
PedidoService  ..>  ItemPedido 
PedidoService  ..>  Pedido 
PedidoService  ..>  PedidoException 
Produto  ..>  ProdutoException 
ProdutoException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
ProdutoService  ..>  Produto 
```

---