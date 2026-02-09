# Sistema de Gerenciamento de Biblioteca

Sistema simples de biblioteca desenvolvido para aprendizado de Clean Architecture, TDD e DDD.

## Sobre o Projeto

Este é um projeto de estudos que implementa um sistema de gerenciamento de biblioteca onde administradores podem gerenciar livros, exemplares, usuários e empréstimos.

## Funcionalidades

- CRUD de Livros (com busca por nome, autor e categoria)
- CRUD de Usuários
- Gerenciamento de Exemplares (cópias físicas dos livros)
- Sistema de Empréstimos
- Autenticação JWT para administradores

## Tecnologias

- Java 17+
- Spring Boot 3.x
- PostgreSQL
- Docker
- Flyway (migrations)
- JUnit 5, Mockito, AssertJ (testes)

## Arquitetura

O projeto segue Clean Architecture com as seguintes camadas:

- **domain**: Entidades e regras de negócio
- **application**: Casos de uso e DTOs
- **infrastructure**: Implementações técnicas (JPA, segurança)
- **presentation**: Controllers REST

## Como Executar

1. Clone o repositório
2. Configure o arquivo `.env`
3. Inicie o PostgreSQL: `docker-compose up -d`
4. Execute a aplicação: `./mvnw spring-boot:run`

A API estará disponível em `http://localhost:8080`

## Testes

Execute os testes com:
```bash
./mvnw test
```

## Regras de Negócio

- Livros podem ter múltiplos exemplares
- Cada exemplar pode ser emprestado para apenas um usuário por vez
- Empréstimos têm prazo fixo de 14 dias
- Apenas administradores acessam o sistema
- Usuários comuns são cadastrados mas não fazem login


**Observação**: Este é um projeto em desenvolvimento criado para fins educacionais e portfólio.
