# 📘 Journal do Projeto - Biblioteca (Clean Architecture, TDD e DDD)
  
---  

## 🚀 Sprint 1

<details>  
<summary><strong>Sprint 1: Fundação do Projeto e Criação do Book</strong></summary>  

<br>  

### 📌 Decisões Iniciais

Minhas escolhas para o banco de dados foram o **Postgres com DBeaver**, pois já tenho uma certa familiaridade e também porque já tenho o Postgres rodando no meu Docker.

Minha escolha de arquitetura foi a **Clean Architecture**, por conta do aprendizado, para poder entender melhor como ela funciona e como são organizadas suas camadas.

Utilizei o **Spring Initializr** para iniciar meu projeto, utilizando as extensões padrões para projetos web, como Spring Web até o Lombok para eliminar código boilerplate.    
Também utilizei:

- Flyway para migrations
- DevTools para melhor produtividade
- Validation para validar meus dados
- Spring Security para segurança
- dotenv-java para variáveis de ambiente

Preferi seguir com o `application.yml` em vez do `.properties`, por questão de legibilidade e também porque foi o formato mais utilizado no curso que eu fiz, então estou mais acostumado.

Nessa primeira Sprint tive que criar tudo relacionado ao **Livro**, sempre seguindo o TDD.
  
---  

### 🏗️ Ordem de Criação

**Migration para criar a tabela no meu DB**

```sql  
CREATE TABLE books (  
    id BIGSERIAL PRIMARY KEY,  
    title VARCHAR(255) NOT NULL,  
    author VARCHAR(255) NOT NULL,  
    category VARCHAR(100) NOT NULL,  
    isbn VARCHAR(20) NOT NULL UNIQUE,  
    created_at TIMESTAMP DEFAULT NOW(),  
    updated_at TIMESTAMP DEFAULT NOW()  
);
```

Depois disso:

- Entidade Book no Core, seguindo TDD

- Entidade BookEntity (Persistence JPA), sem TDD

- Mapper da entidade JPA com TDD

- Repository Interface da entidade Core

- RepositoryImpl implementando o repositório do Core

- DTOs de Request e Response

- Mapper da entidade Core para Request e Response DTO

- UseCases com TDD para o famoso CRUD

- Controller para os endpoints

- DTO para tratamento de erros

- GlobalExceptionHandler para tratar os erros


---

## ⚔️ Desafios

Tive um pouco de dificuldade no TDD (Test-Driven Development), pois achei que tudo teria que ter teste unitário, mas depois vi que nem tudo é TDD com teste unitário — alguns são testes de integração.

Então foquei apenas no que realmente precisava de teste unitário e pensei em deixar a parte de testes de integração para outra Sprint, refatorando o código depois.

Os testes foram tranquilos na criação do Book no Core. Depois que fiz os testes, percebi que precisava implementar também o DDD (Domain-Driven Design) na minha entidade. Foi aí que pesquisei bastante e decidi criar os Value Objects para o ISBN e também implementar que alguns atributos do Book não podem ser nulos diretamente nos construtores.

Sobre os Value Objects, decidi aplicar apenas no ISBN. Pesquisei e descobri que existem dois tipos: ISBN-10 e ISBN-13 (mais recente).

Optei pelo ISBN-13 e também decidi seguir um padrão onde todo livro tem que ser brasileiro, então o ISBN deve começar com 978 ou 979 seguido por 85. Para isso, precisei utilizar regex para padronizar.

Sobre o regex do ISBN, utilizei um pronto e fiz algumas alterações. Tive que olhar um repositório no GitHub que seguiu praticamente o mesmo padrão que o meu. Não me aprofundei muito em regex, estudei o básico e reaproveitei parte do código.

No Value Object tive bastante dificuldade com TDD. Foi um pouco complicado, mas depois de algum estudo consegui resolver e criar os testes.

---

### 🔄 Mapper e Ajustes

Indo para a entidade da Application, não tive muita dificuldade, pois já fiz bastante CRUD com arquitetura monolítica no curso.

O que pegou um pouco foi o mapper. Eu poderia simplesmente utilizar a anotação `@Mapper`, mas decidi me aprofundar mais e entender melhor como funciona.

Criei os testes, mas tive um pouco de dificuldade porque, em vez de testar a conversão, estava testando o mapper de forma errada. Precisei revisar e corrigir.

Depois percebi que tinha um problema nos meus DTOs e no meu BookEntity (Application). Eu estava esperando um objeto ISBN em vez de uma String e depois convertendo para objeto utilizando o mapper.

Tive que voltar atrás no código e resolver isso para conseguir criar o mapper sem problemas.

No meu caso:

- O RequestDTO recebe uma String

- O mapper transforma em um objeto ISBN que valida se o valor é válido

- O Response retorna uma String novamente

- No banco de dados, é salvo como String


---

## 🧠 UseCases

No primeiro UseCase, o CreateUseCase, foi onde o “bicho pegou”.

Como estava começando com TDD, tive dificuldade em entender bem o conceito de AAA (Arrange, Act, Assert). Fiz os outros testes, mas eram mais tranquilos.

Depois de ver alguns vídeos e pesquisar mais sobre o assunto, consegui entender melhor. O primeiro teste foi feito e depois criei o UseCase. Depois disso, o restante ficou mais tranquilo.

Até chegar no UpdateUseCase.

O TDD dele foi tranquilo, mas me perdi um pouco na lógica. Eu queria que, se ele recebesse algum campo em branco ou null, ele não alterasse nada naquele campo do Book.

Por exemplo: se receber apenas o autor e o resto em branco, ele altera apenas o autor.

Revisei alguns projetos antigos meus, segui a lógica que já tinha usado antes e consegui resolver esse problema.

---

## 🌐 Controllers

Os controllers foram tranquilos, pois utilizei o Response para tirar a responsabilidade do Core.

Apenas criei os endpoints e fiz o mapeamento. Como já tinha feito outros CRUDs no curso, essa parte foi bem tranquila.

---

## 🚨 GlobalExceptionHandler

No GlobalExceptionHandler tive que pesquisar novamente.

Mesmo já tendo feito em outro projeto, tinha feito apenas uma vez e ainda não tinha entendido 100% o conceito.

Depois de pesquisar um pouco, consegui entender melhor. Foi em um artigo que eu li (deveria ter salvo o link, pois tinha mais coisas interessantes lá) que vi que deveria criar um DTO específico para padronizar as respostas de erro.

Depois que entendi que eu crio um método que intercepta a exception e retorna aquilo que eu definir no handler, lembrei que em um projeto antigo eu tinha utilizado um `Map<String, String>`.

Aqui eu consegui entender melhor a importância de usar um DTO para padronizar as respostas.

---

## ✅ Final da Sprint 1

Depois de tudo isso, testei meus endpoints com Postman.

A sensação de ver tudo funcionando, apesar das dificuldades, é incrível. Fiquei feliz.

Mas também lembrei que isso foi apenas o começo.

Agora estou partindo para a Sprint 2.

</details> 