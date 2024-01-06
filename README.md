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
  + main(String[]) void
}
class Cliente {
  + Cliente(String, String) 
  - String nome
  - Endereco endereco
  - Boolean ativo
  - String INFORMAR_UM_NOME
  - String INFORMAR_UM_ID
  - String ADICIONAR_UM_ENDERECO
  - String id
  + setEndereco(Endereco) void
  + ativarCliente() void
  - validarDados() void
  + desativarCliente() void
  + alterarNome(String) void
}
class ClienteException {
  + ClienteException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class Endereco {
  + Endereco(String, Integer, String, String) 
  - String NUMERO_RESIDENCIA_OBRIGATORIO
  - Integer numero
  - String CEP_REQUIRED
  - String cep
  - String cidade
  - String rua
  - String RUA_NOME_AUSENTE
  - String CIDADE_NOME_AUSENTE
  - validarDados() void
  + toString() String
}
class EnderecoException {
  + EnderecoException(String) 
  + ManipuladorDeMensagensDeErros ERRO
}
class ItemPedido {
  + ItemPedido(String, String, BigDecimal) 
  - String name
  - String id
  - BigDecimal preco
  + toString() String
}
class ManipuladorDeMensagensDeErros {
<<enumeration>>
  - ManipuladorDeMensagensDeErros(String) 
  +  CLIENTE_INVALIDO
  +  ENDERECO_INVALIDO
  - String erro
  + mensagem(String) String
}
class Pedido {
  + Pedido(String, String, List~ItemPedido~) 
  - String id
  - String clienteId
  - List~ItemPedido~ itens
  + toString() String
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
Pedido "1" *--> "itens *" ItemPedido 

```

---