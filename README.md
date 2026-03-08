# BookStack: Seu Sistema Completo de Gerenciamento de Biblioteca


Este é um projeto de estudos que implementa um sistema de gerenciamento de biblioteca onde administradores podem gerenciar livros, exemplares, usuários e empréstimos, aprofundado em **Clean Architecture**, **Test-Driven Development (TDD)** e **Domain-Driven Design (DDD)**

## 📚 Documentação de Aprendizado

Durante o desenvolvimento do projeto estou documentando minha evolução, decisões técnicas, dificuldades e aprendizados em formato de journal.

❗(Lembrando que vou escrevendo durante o processo de desenvolvimento e peço para IA resumir e destacar os pontos mais importantes.)❗

👉 [Acompanhar Journal de Desenvolvimento](journal.md)

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
4. Execute a aplicação: `c`

A  API estará disponível em `http://localhost:8080`

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

## 💡 Próximos Passos e Melhorias Futuras

Este projeto está em constante evolução. Algumas ideias para futuras melhorias incluem:

- Implementação de um frontend para uma experiência de usuário completa.
- Integração com serviços de email para notificações de empréstimos.
- Implementação de testes de integração mais abrangentes.

**Observação**: Este é um projeto em desenvolvimento criado para fins educacionais e portfólio.


