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
class BuilderMapper {
<<Interface>>
  + mapper(Class~T~, Object) T
  + mapper(Class~T~, E, MapperStrategy~T, E~) T
}
class Cliente {
  + Cliente(String, String) 
  - String id
  - Integer pontosDeRecompensa
  - String INFORMAR_UM_NOME
  - String ADICIONAR_UM_ENDERECO
  - Boolean ativo
  - String INFORMAR_UM_ID
  - Endereco endereco
  - String nome
  + desativarCliente() void
  + getPontosDeRecompensa() Integer
  + toString() String
  + getId() String
  - validarDados() void
  + ativarCliente() void
  + getEndereco() Endereco
  + alterarNome(String) void
  + getAtivo() Boolean
  + adicionarEndereco(Endereco) void
  + adicionarPontosDeRecompensa(Integer) void
  + getNome() String
}
class ClienteEntity {
  - ClienteEntity(Builder) 
  + ClienteEntity() 
  + ClienteEntity(String, String, Boolean, Integer, String, Integer, String, String) 
  - String nome
  - Boolean ativo
  - String cep
  - String id
  - Integer pontosDeRecompensa
  - String rua
  - Integer numero
  - String cidade
  + getPontosDeRecompensa() Integer
  + getId() String
  + getCep() String
  + getNome() String
  + getRua() String
  + getAtivo() Boolean
  + getNumero() Integer
  + toString() String
  + getCidade() String
}
class ClienteEntityFromClienteMapper {
  + ClienteEntityFromClienteMapper() 
  + mapper(Cliente) ClienteEntity
}
class ClienteEntityRepository {
  + ClienteEntityRepository() 
  - String FALHA_AO_ATUALIZAR_O_CLIENTE
  - String FALHA_AO_DELETAR_O_CLIENTE
  - String CLIENTE_NULO_EXCEPTION_MESSAGE
  - MapperStrategy~Cliente, ClienteEntity~ clienteFromClienteEntityMapper
  - String CLIENTE_BUSCA_ID_ERRO
  - MapperStrategy~ClienteEntity, Cliente~ clienteEntityFromClienteMapper
  - String FALHA_AO_SALVAR_ENTIDADE_CLIENTE
  - String FALHA_AO_BUSCAR_TODOS_OS_CLIENTES
  - String CLIENTE_ID_NULO
  + deletar(Cliente) void
  + buscarPorId(String) Cliente
  + guardar(Cliente) void
  + atualizar(Cliente) void
  + buscarTodos() List~Cliente~
}
class ClienteException {
  + ClienteException(String) 
  + ClienteException(String, Throwable) 
  + ManipuladorDeMensagensDeErros ERRO
}
class ClienteFromClienteEntityMapper {
  + ClienteFromClienteEntityMapper() 
  + mapper(ClienteEntity) Cliente
}
class ClienteRepository {
<<Interface>>

}
class Endereco {
  + Endereco(String, Integer, String, String) 
  - String RUA_NOME_AUSENTE
  - String rua
  - Integer numero
  - String cep
  - String CEP_REQUIRED
  - String CIDADE_NOME_AUSENTE
  - String cidade
  - String NUMERO_RESIDENCIA_OBRIGATORIO
  + getNumero() Integer
  + toString() String
  + getRua() String
  + getCidade() String
  - validarDados() void
  + getCep() String
}
class EnderecoException {
  + EnderecoException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class HibernateConnectionSingleton {
  - HibernateConnectionSingleton() 
  - SessionFactory sessionFactory
  - getSessionFactory(Configuration) SessionFactory
  + getSession() Session
  + closeSessionFactory() void
}
class ItemException {
  + ItemException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class ItemPedido {
  + ItemPedido(String, String, BigDecimal, String, Integer) 
  - String PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO
  - String PRODUCT_ID_FIELD_REQUIRED
  - String NAME_FIELD_REQUIRED
  - String id
  - String produtoId
  - String QUANTITY_MUST_BE_GREATER_THAN_ZERO
  - Integer quantidade
  - String ID_FIELD_REQUIRED
  - String name
  - BigDecimal preco
  + getName() String
  + getProdutoId() String
  + getId() String
  + getPreco() BigDecimal
  - validarDados() void
  + getQuantidade() Integer
  + toString() String
}
class ItemPedidoEntity {
  + ItemPedidoEntity(String, String, String, BigDecimal, Integer, PedidoEntity) 
  - ItemPedidoEntity(Builder) 
  + ItemPedidoEntity() 
  - String produtoId
  - Integer quantidade
  - String id
  - PedidoEntity pedido
  - String name
  - BigDecimal preco
  + getPreco() BigDecimal
  + getQuantidade() Integer
  + getName() String
  + getProdutoId() String
  + getId() String
  + getPedido() PedidoEntity
}
class ItemPedidoEntityFromItemPedido {
  + ItemPedidoEntityFromItemPedido() 
  + mapper(ItemPedido) ItemPedidoEntity
}
class ItemPedidoFromItemPedidoEntity {
  + ItemPedidoFromItemPedidoEntity() 
  + mapper(ItemPedidoEntity) ItemPedido
}
class ManipuladorDeMensagensDeErros {
<<enumeration>>
  - ManipuladorDeMensagensDeErros(String) 
  +  PEDIDO_INVALIDO
  +  PRODUTO_INVALIDO
  +  ITEM_PEDIDO_INVALIDO
  +  CLIENTE_INVALIDO
  - String erro
  +  ENDERECO_INVALIDO
  + mensagem(String) String
}
class MapperStrategy~T, E~ {
<<Interface>>
  + mapper(E) T
}
class Pedido {
  + Pedido(String, String, List~ItemPedido~) 
  - String ID_INVALIDO
  - String INVALID_CLIENT_CODE
  - BigDecimal total
  - String id
  - List~ItemPedido~ itens
  - String clienteId
  - String INVALID_ITEM_LIST
  + calcularCustoTotal() BigDecimal
  + toString() String
  + getClienteId() String
  + getId() String
  - validarPedido() void
  + getItens() List~ItemPedido~
}
class PedidoEntity {
  - PedidoEntity(Builder) 
  + PedidoEntity() 
  + PedidoEntity(String, String, BigDecimal, List~ItemPedidoEntity~) 
  - String id
  - String clienteId
  - BigDecimal total
  - List~ItemPedidoEntity~ itens
  + toString() String
  + getClienteId() String
  + getTotal() BigDecimal
  + getId() String
  + getItens() List~ItemPedidoEntity~
}
class PedidoEntityFromPedidoMapper {
  + PedidoEntityFromPedidoMapper() 
  + mapper(Pedido) PedidoEntity
}
class PedidoEntityRepository {
  + PedidoEntityRepository() 
  - String OBJETO_PEDIDO_NULO
  - String ID_PEDIDO_OBRIGATORIO
  - MapperStrategy~Pedido, PedidoEntity~ pedidoFromPedidoEntity
  - String ERROR_SAVING_PEDIDO_ENTITY
  - String ID_NAO_ENCONTRADO
  - MapperStrategy~PedidoEntity, Pedido~ pedidoEntityFromPedido
  - String ERRO_AO_BUSCAR_PEDIDO
  - String ERROR_DELETAR_PEDIDO
  - String FALHA_ATUALIZACAO_PEDIDO
  + guardar(Pedido) void
  + buscarPorId(String) Pedido
  + atualizar(Pedido) void
  + deletar(Pedido) void
  + buscarTodos() List~Pedido~
}
class PedidoException {
  + PedidoException(String) 
  + PedidoException(String, Throwable) 
  + ManipuladorDeMensagensDeErros ERRO
}
class PedidoFromPedidoEntityMapper {
  + PedidoFromPedidoEntityMapper() 
  + mapper(PedidoEntity) Pedido
}
class PedidoRepository {
<<Interface>>

}
class PedidoService {
  - PedidoService() 
  - String CLIENTE_OBRIGATORIO
  + fazerEncomenda(Cliente, List~ItemPedido~) Pedido
  + calcularValorTotalDosPedidos(List~Pedido~) BigDecimal
}
class Produto {
  + Produto(String, String, BigDecimal) 
  - BigDecimal preco
  - String ID_REQUIRED
  - String PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO
  - String id
  - String nome
  - String PRODUCT_NAME_REQUIRED
  + toString() String
  + alterarPreco(BigDecimal) void
  + getPreco() BigDecimal
  + getNome() String
  + getId() String
  - validarDados() void
  + alterarNome(String) void
}
class ProdutoEntity {
  + ProdutoEntity() 
  + ProdutoEntity(String, String, BigDecimal) 
  - BigDecimal preco
  - String id
  - String nome
  + getNome() String
  + getPreco() BigDecimal
  + getId() String
}
class ProdutoEntityFromProdudoMapper {
  + ProdutoEntityFromProdudoMapper() 
  + mapper(Produto) ProdutoEntity
}
class ProdutoEntityRepository {
  + ProdutoEntityRepository() 
  - String FALHA_AO_BUSCAR_TODOS_OS_PRODUTOS
  - String OBJETO_PRODUTO_NECESSARIO
  - String PRODUTO_NAO_ENCONTRADO
  - String FALHA_AO_SALVAR_ENTIDADE_PRODUTO
  - MapperStrategy~ProdutoEntity, Produto~ produtoEntityFromProdutoMapper
  - String FALHA_AO_ATUALIZAR_ENTIDADE_PRODUTO
  - String FALHA_AO_DELETAR_ENTIDADE_PRODUTO
  - MapperStrategy~Produto, ProdutoEntity~ produtoFromProdutoEntityMapper
  - String PRODUTO_ID_NULO
  + buscarPorId(String) Produto
  + guardar(Produto) void
  + atualizar(Produto) void
  + deletar(Produto) void
  + buscarTodos() List~Produto~
}
class ProdutoException {
  + ProdutoException(String) 
  + ProdutoException(String, Throwable) 
  + ManipuladorDeMensagensDeErros ERRO
}
class ProdutoFromProdutoEntityMapper {
  + ProdutoFromProdutoEntityMapper() 
  + mapper(ProdutoEntity) Produto
}
class ProdutoRepository {
<<Interface>>

}
class ProdutoService {
  - ProdutoService() 
  - double PERCENTAGE_BASE
  + aplicarAumentoPercentualAoPrecoDosProdutos(List~Produto~, Double) void
}
class RepositoryContract~T~ {
<<Interface>>
  + buscarTodos() List~T~
  + deletar(T) void
  + buscarPorId(String) T
  + guardar(T) void
  + atualizar(T) void
}

App  ..>  ItemPedido 
App  ..>  Pedido 
App  ..>  PedidoEntityRepository 
BuilderMapper  ..>  MapperStrategy~T, E~ 
Cliente  ..>  ClienteException 
Cliente "1" *--> "endereco 1" Endereco 
ClienteEntityFromClienteMapper  ..>  Cliente 
ClienteEntityFromClienteMapper  ..>  ClienteEntity 
ClienteEntityFromClienteMapper  ..>  Endereco 
ClienteEntityFromClienteMapper  ..>  MapperStrategy~T, E~ 
ClienteEntityRepository  ..>  Cliente 
ClienteEntityRepository  ..>  ClienteEntity 
ClienteEntityRepository  ..>  ClienteEntityFromClienteMapper 
ClienteEntityRepository  ..>  ClienteException 
ClienteEntityRepository  ..>  ClienteFromClienteEntityMapper 
ClienteEntityRepository  ..>  ClienteRepository 
ClienteEntityRepository  ..>  HibernateConnectionSingleton 
ClienteEntityRepository "1" *--> "clienteEntityFromClienteMapper 1" MapperStrategy~T, E~ 
ClienteException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
ClienteFromClienteEntityMapper  ..>  Cliente 
ClienteFromClienteEntityMapper  ..>  ClienteEntity 
ClienteFromClienteEntityMapper  ..>  Endereco 
ClienteFromClienteEntityMapper  ..>  MapperStrategy~T, E~ 
ClienteRepository  ..>  Cliente 
ClienteRepository  -->  RepositoryContract~T~ 
Endereco  ..>  EnderecoException 
EnderecoException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
ItemException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
ItemPedido  ..>  ItemException 
ItemPedidoEntity "1" *--> "pedido 1" PedidoEntity 
ItemPedidoEntityFromItemPedido  ..>  ItemPedido 
ItemPedidoEntityFromItemPedido  ..>  ItemPedidoEntity 
ItemPedidoEntityFromItemPedido  ..>  MapperStrategy~T, E~ 
ItemPedidoFromItemPedidoEntity  ..>  ItemPedido 
ItemPedidoFromItemPedidoEntity  ..>  ItemPedidoEntity 
ItemPedidoFromItemPedidoEntity  ..>  MapperStrategy~T, E~ 
Pedido "1" *--> "itens *" ItemPedido 
Pedido  ..>  PedidoException 
PedidoEntity "1" *--> "itens *" ItemPedidoEntity 
PedidoEntityFromPedidoMapper  ..>  ItemPedido 
PedidoEntityFromPedidoMapper  ..>  ItemPedidoEntity 
PedidoEntityFromPedidoMapper  ..>  ItemPedidoEntityFromItemPedido 
PedidoEntityFromPedidoMapper  ..>  MapperStrategy~T, E~ 
PedidoEntityFromPedidoMapper  ..>  Pedido 
PedidoEntityFromPedidoMapper  ..>  PedidoEntity 
PedidoEntityRepository  ..>  HibernateConnectionSingleton 
PedidoEntityRepository "1" *--> "pedidoEntityFromPedido 1" MapperStrategy~T, E~ 
PedidoEntityRepository  ..>  Pedido 
PedidoEntityRepository  ..>  PedidoEntity 
PedidoEntityRepository  ..>  PedidoEntityFromPedidoMapper 
PedidoEntityRepository  ..>  PedidoException 
PedidoEntityRepository  ..>  PedidoFromPedidoEntityMapper 
PedidoEntityRepository  ..>  PedidoRepository 
PedidoException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
PedidoFromPedidoEntityMapper  ..>  BuilderMapper 
PedidoFromPedidoEntityMapper  ..>  ItemPedido 
PedidoFromPedidoEntityMapper  ..>  ItemPedidoEntity 
PedidoFromPedidoEntityMapper  ..>  ItemPedidoFromItemPedidoEntity 
PedidoFromPedidoEntityMapper  ..>  MapperStrategy~T, E~ 
PedidoFromPedidoEntityMapper  ..>  Pedido 
PedidoFromPedidoEntityMapper  ..>  PedidoEntity 
PedidoRepository  ..>  Pedido 
PedidoRepository  -->  RepositoryContract~T~ 
PedidoService  ..>  Cliente 
PedidoService  ..>  ItemPedido 
PedidoService  ..>  Pedido 
PedidoService  ..>  PedidoException 
Produto  ..>  ProdutoException 
ProdutoEntityFromProdudoMapper  ..>  MapperStrategy~T, E~ 
ProdutoEntityFromProdudoMapper  ..>  Produto 
ProdutoEntityFromProdudoMapper  ..>  ProdutoEntity 
ProdutoEntityRepository  ..>  HibernateConnectionSingleton 
ProdutoEntityRepository "1" *--> "produtoEntityFromProdutoMapper 1" MapperStrategy~T, E~ 
ProdutoEntityRepository  ..>  Produto 
ProdutoEntityRepository  ..>  ProdutoEntity 
ProdutoEntityRepository  ..>  ProdutoEntityFromProdudoMapper 
ProdutoEntityRepository  ..>  ProdutoException 
ProdutoEntityRepository  ..>  ProdutoFromProdutoEntityMapper 
ProdutoEntityRepository  ..>  ProdutoRepository 
ProdutoException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
ProdutoFromProdutoEntityMapper  ..>  MapperStrategy~T, E~ 
ProdutoFromProdutoEntityMapper  ..>  Produto 
ProdutoFromProdutoEntityMapper  ..>  ProdutoEntity 
ProdutoRepository  ..>  Produto 
ProdutoRepository  -->  RepositoryContract~T~ 
ProdutoService  ..>  Produto 
```

---