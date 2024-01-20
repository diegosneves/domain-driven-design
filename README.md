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
## Domain

### Entities
```mermaid
classDiagram
direction BT
class Cliente {
  + Cliente(String, String) 
  - String id
  - Endereco endereco
  - Integer pontosDeRecompensa
  - String nome
  - Boolean ativo
  + getId() String
  + alterarNome(String) void
  + getPontosDeRecompensa() Integer
  + ativarCliente() void
  + adicionarPontosDeRecompensa(Integer) void
  - validarDados() void
  + getNome() String
  + toString() String
  + getEndereco() Endereco
  + getAtivo() Boolean
  + desativarCliente() void
  + adicionarEndereco(Endereco) void
}
class Endereco {
  + Endereco(String, Integer, String, String) 
  - String rua
  - Integer numero
  - String cep
  - String cidade
  + getNumero() Integer
  + getRua() String
  - validarDados() void
  + getCep() String
  + getCidade() String
  + toString() String
}
class ItemPedido {
  + ItemPedido(String, String, BigDecimal, String, Integer) 
  - String id
  - String produtoId
  - String name
  - BigDecimal preco
  - Integer quantidade
  + getProdutoId() String
  - validarDados() void
  + getId() String
  + getQuantidade() Integer
  + getPreco() BigDecimal
  + getName() String
  + toString() String
}
class Pedido {
  + Pedido(String, String, List~ItemPedido~) 
  - String clienteId
  - BigDecimal total
  - String id
  - List~ItemPedido~ itens
  + toString() String
  - validarPedido() void
  + calcularCustoTotal() BigDecimal
  + getId() String
  + getItens() List~ItemPedido~
  + getClienteId() String
}
class Produto {
  + Produto(String, String, BigDecimal) 
  - String nome
  - BigDecimal preco
  - String id
  + getId() String
  - validarDados() void
  + alterarNome(String) void
  + getNome() String
  + toString() String
  + getPreco() BigDecimal
  + alterarPreco(BigDecimal) void
}

Cliente "1" *--> "endereco 1" Endereco 
Pedido "1" *--> "itens *" ItemPedido 
```
### Events

```mermaid
classDiagram
direction BT
class DistribuidorEventos {
  - DistribuidorEventos() 
  - Map~String, List~ManipuladorEventoContrato~Evento~~~ processos
  + notificar(T) void
  + remover(ManipuladorEventoContrato~?~) void
  + removerTodos() void
  + registrar(ManipuladorEventoContrato~Evento~) void
}
class EnviarEmailAoCriarClienteHandler {
  + EnviarEmailAoCriarClienteHandler() 
  + processarEvento(EventoCriarCliente) void
  + eventName() String
}
class EnviarEmailAoCriarProdutoHandler {
  + EnviarEmailAoCriarProdutoHandler() 
  + processarEvento(EventoCriarProduto) void
  + eventName() String
}
class Evento~T~ {
  # Evento(T) 
  # LocalDateTime data
  # String objectName
  # T dadosDoEvento
  + getDadosDoEvento() T
  + getData() LocalDateTime
  + getObjectName() String
}
class EventoCriarCliente {
  + EventoCriarCliente(Cliente) 
}
class EventoCriarProduto {
  + EventoCriarProduto(Produto) 
}
class EventoEnderecoAlterado {
  + EventoEnderecoAlterado(Cliente) 
}
class ManipuladorEventoContrato~T~ {
<<Interface>>
  + eventName() String
  + processarEvento(T) void
}
class NotificarAlteracaoEnderecoCliente {
  + NotificarAlteracaoEnderecoCliente() 
  + eventName() String
  + processarEvento(EventoEnderecoAlterado) void
}

DistribuidorEventos  ..>  Evento~T~ 
DistribuidorEventos  ..>  ManipuladorEventoContrato~T~ 
EnviarEmailAoCriarClienteHandler  ..>  Evento~T~ 
EnviarEmailAoCriarClienteHandler  ..>  EventoCriarCliente 
EnviarEmailAoCriarClienteHandler  ..>  ManipuladorEventoContrato~T~ 
EnviarEmailAoCriarProdutoHandler  ..>  Evento~T~ 
EnviarEmailAoCriarProdutoHandler  ..>  EventoCriarProduto 
EnviarEmailAoCriarProdutoHandler  ..>  ManipuladorEventoContrato~T~ 
EventoCriarCliente  -->  Evento~T~ 
EventoCriarProduto  -->  Evento~T~ 
EventoEnderecoAlterado  -->  Evento~T~ 
ManipuladorEventoContrato~T~  ..>  Evento~T~ 
NotificarAlteracaoEnderecoCliente  ..>  Evento~T~ 
NotificarAlteracaoEnderecoCliente  ..>  EventoEnderecoAlterado 
NotificarAlteracaoEnderecoCliente  ..>  ManipuladorEventoContrato~T~ 

```

### Repositories
```mermaid
classDiagram
direction BT
class ClienteRepository {
<<Interface>>

}
class PedidoRepository {
<<Interface>>

}
class ProdutoRepository {
<<Interface>>

}
class RepositoryContract~T~ {
<<Interface>>
  + guardar(T) void
  + buscarPorId(String) T
  + buscarTodos() List~T~
  + atualizar(T) void
  + deletar(T) void
}

ClienteRepository  -->  RepositoryContract~T~ 
PedidoRepository  -->  RepositoryContract~T~ 
ProdutoRepository  -->  RepositoryContract~T~ 
```

### Services
```mermaid
classDiagram
direction BT
class PedidoService {
  - PedidoService() 
  + calcularValorTotalDosPedidos(List~Pedido~) BigDecimal
  + fazerEncomenda(Cliente, List~ItemPedido~) Pedido
}
class ProdutoService {
  - ProdutoService() 
  + aplicarAumentoPercentualAoPrecoDosProdutos(List~Produto~, Double) void
}


```

---

## Exceptions

```mermaid
classDiagram
direction BT
class ClienteException {
  + ClienteException(String, Throwable) 
  + ClienteException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class EnderecoException {
  + EnderecoException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class EventosException {
  + EventosException(String) 
  + EventosException(String, Throwable) 
  + ManipuladorDeMensagensDeErros ERRO
}
class ItemException {
  + ItemException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class ManipuladorDeMensagensDeErros {
<<enumeration>>
  - ManipuladorDeMensagensDeErros(String) 
  +  PEDIDO_INVALIDO
  +  ITEM_PEDIDO_INVALIDO
  +  ENDERECO_INVALIDO
  +  PRODUTO_INVALIDO
  +  CLIENTE_INVALIDO
  - String erro
  +  EVENTOS_CAST
  + mensagem(String) String
}
class PedidoException {
  + PedidoException(String, Throwable) 
  + PedidoException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class ProdutoException {
  + ProdutoException(String, Throwable) 
  + ProdutoException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}

ClienteException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
EnderecoException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
EventosException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
ItemException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
PedidoException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
ProdutoException "1" *--> "ERRO 1" ManipuladorDeMensagensDeErros 
```
---
## Infrastructure

### DB

```mermaid
classDiagram
direction BT
class clientes {
   bit(1) ativo
   varchar(255) cep
   varchar(255) cidade
   varchar(255) nome
   int numero
   int pontos_de_recompensa
   varchar(255) rua
   varchar(255) id
}
class itens_pedido {
   varchar(255) name
   decimal(38,2) preco
   varchar(255) produdo_id
   int quantidade
   varchar(255) itens
   varchar(255) id
}
class pedidos {
   varchar(255) cliente_id
   decimal(38,2) total
   varchar(255) id
}
class pedidos_itens_pedido {
   varchar(255) PedidoEntity_id
   varchar(255) itens_id
}
class produtos {
   varchar(255) nome
   decimal(38,2) preco
   varchar(255) id
}

itens_pedido  -->  pedidos : itens
pedidos  -->  clientes : cliente_id
pedidos_itens_pedido  -->  itens_pedido : itens_id
pedidos_itens_pedido  -->  pedidos : PedidoEntity_id
```

```mermaid
classDiagram
direction BT
class ClienteEntity {
  + ClienteEntity() 
  - ClienteEntity(Builder) 
  + ClienteEntity(String, String, Boolean, Integer, String, Integer, String, String) 
  - String id
  - Integer pontosDeRecompensa
  - Boolean ativo
  - String cep
  - String rua
  - Integer numero
  - String nome
  - String cidade
  + getId() String
  + getPontosDeRecompensa() Integer
  + getNome() String
  + getNumero() Integer
  + toString() String
  + getAtivo() Boolean
  + getCep() String
  + getCidade() String
  + getRua() String
}
class HibernateConnectionSingleton {
  - HibernateConnectionSingleton() 
  - SessionFactory sessionFactory
  + closeSessionFactory() void
  - getSessionFactory(Configuration) SessionFactory
  + getSession() Session
}
class ItemPedidoEntity {
  + ItemPedidoEntity(String, String, String, BigDecimal, Integer, PedidoEntity) 
  - ItemPedidoEntity(Builder) 
  + ItemPedidoEntity() 
  - String id
  - PedidoEntity pedido
  - String name
  - BigDecimal preco
  - Integer quantidade
  - String produtoId
  + getName() String
  + getId() String
  + getPreco() BigDecimal
  + getProdutoId() String
  + getQuantidade() Integer
  + getPedido() PedidoEntity
}
class PedidoEntity {
  + PedidoEntity(String, String, BigDecimal, List~ItemPedidoEntity~) 
  - PedidoEntity(Builder) 
  + PedidoEntity() 
  - String clienteId
  - String id
  - List~ItemPedidoEntity~ itens
  - BigDecimal total
  + getId() String
  + getClienteId() String
  + getTotal() BigDecimal
  + toString() String
  + getItens() List~ItemPedidoEntity~
}
class ProdutoEntity {
  + ProdutoEntity(String, String, BigDecimal) 
  + ProdutoEntity() 
  - BigDecimal preco
  - String id
  - String nome
  + getNome() String
  + getId() String
  + getPreco() BigDecimal
}

ItemPedidoEntity "1" *--> "pedido 1" PedidoEntity 
PedidoEntity "1" *--> "itens *" ItemPedidoEntity 
```

---

## Mappers
```mermaid
classDiagram
direction BT
class BuilderMapper {
<<Interface>>
  + mapper(Class~T~, Object) T
  + mapper(Class~T~, E, MapperStrategy~T, E~) T
}
class ClienteEntityFromClienteMapper {
  + ClienteEntityFromClienteMapper() 
  + mapper(Cliente) ClienteEntity
}
class ClienteFromClienteEntityMapper {
  + ClienteFromClienteEntityMapper() 
  + mapper(ClienteEntity) Cliente
}
class ItemPedidoEntityFromItemPedido {
  + ItemPedidoEntityFromItemPedido() 
  + mapper(ItemPedido) ItemPedidoEntity
}
class ItemPedidoFromItemPedidoEntity {
  + ItemPedidoFromItemPedidoEntity() 
  + mapper(ItemPedidoEntity) ItemPedido
}
class MapperStrategy~T; E~ {
<<Interface>>
  + mapper(E) T
}
class PedidoEntityFromPedidoMapper {
  + PedidoEntityFromPedidoMapper() 
  + mapper(Pedido) PedidoEntity
}
class PedidoFromPedidoEntityMapper {
  + PedidoFromPedidoEntityMapper() 
  + mapper(PedidoEntity) Pedido
}
class ProdutoEntityFromProdudoMapper {
  + ProdutoEntityFromProdudoMapper() 
  + mapper(Produto) ProdutoEntity
}
class ProdutoFromProdutoEntityMapper {
  + ProdutoFromProdutoEntityMapper() 
  + mapper(ProdutoEntity) Produto
}

BuilderMapper  ..>  MapperStrategy~T; E~ 
ClienteEntityFromClienteMapper  ..>  MapperStrategy~T; E~ 
ClienteFromClienteEntityMapper  ..>  MapperStrategy~T; E~ 
ItemPedidoEntityFromItemPedido  ..>  MapperStrategy~T; E~ 
ItemPedidoFromItemPedidoEntity  ..>  MapperStrategy~T; E~ 
PedidoEntityFromPedidoMapper  ..>  ItemPedidoEntityFromItemPedido 
PedidoEntityFromPedidoMapper  ..>  MapperStrategy~T; E~ 
PedidoFromPedidoEntityMapper  ..>  BuilderMapper 
PedidoFromPedidoEntityMapper  ..>  ItemPedidoFromItemPedidoEntity 
PedidoFromPedidoEntityMapper  ..>  MapperStrategy~T; E~ 
ProdutoEntityFromProdudoMapper  ..>  MapperStrategy~T; E~ 
ProdutoFromProdutoEntityMapper  ..>  MapperStrategy~T; E~ 
```

---
## Repository
```mermaid
classDiagram
direction BT
class ClienteEntityRepository {
  + ClienteEntityRepository() 
  - MapperStrategy~ClienteEntity, Cliente~ clienteEntityFromClienteMapper
  - MapperStrategy~Cliente, ClienteEntity~ clienteFromClienteEntityMapper
  + atualizar(Cliente) void
  + buscarPorId(String) Cliente
  + buscarTodos() List~Cliente~
  + guardar(Cliente) void
  + deletar(Cliente) void
}
class ClienteRepository {
<<Interface>>

}
class PedidoEntityRepository {
  + PedidoEntityRepository() 
  - MapperStrategy~PedidoEntity, Pedido~ pedidoEntityFromPedido
  - MapperStrategy~Pedido, PedidoEntity~ pedidoFromPedidoEntity
  + buscarPorId(String) Pedido
  + guardar(Pedido) void
  + deletar(Pedido) void
  + buscarTodos() List~Pedido~
  + atualizar(Pedido) void
}
class PedidoRepository {
<<Interface>>

}
class ProdutoEntityRepository {
  + ProdutoEntityRepository() 
  - MapperStrategy~ProdutoEntity, Produto~ produtoEntityFromProdutoMapper
  - MapperStrategy~Produto, ProdutoEntity~ produtoFromProdutoEntityMapper
  + guardar(Produto) void
  + atualizar(Produto) void
  + deletar(Produto) void
  + buscarPorId(String) Produto
  + buscarTodos() List~Produto~
}
class ProdutoRepository {
<<Interface>>

}

ClienteEntityRepository  ..>  ClienteRepository 
PedidoEntityRepository  ..>  PedidoRepository 
ProdutoEntityRepository  ..>  ProdutoRepository 
```

---
