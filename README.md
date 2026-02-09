# Library Management System

Simple library system developed for learning Clean Architecture, TDD, and DDD.

## About the Project

This is a learning project that implements a library management system where administrators can manage books, copies, users, and loans.

## Features

- Books CRUD (with search by name, author, and category)
- Users CRUD
- Copies Management (physical book copies)
- Loan System
- JWT Authentication for administrators

## Technologies

- Java 17+
- Spring Boot 3.x
- PostgreSQL
- Docker
- Flyway (migrations)
- JUnit 5, Mockito, AssertJ (testing)

## Architecture

The project follows Clean Architecture with the following layers:

- **domain**: Entities and business rules
- **application**: Use cases and DTOs
- **infrastructure**: Technical implementations (JPA, security)
- **presentation**: REST Controllers

## How to Run

1. Clone the repository
2. Configure the `.env`
3. Start PostgreSQL: `docker-compose up -d`
4. Run the application: `./mvnw spring-boot:run`

The API will be available at `http://localhost:8080`

## Tests

Run tests with:
```bash
./mvnw test
```

## Business Rules

- Books can have multiple copies
- Each copy can be loaned to only one user at a time
- Loans have a fixed period of 14 days
- Only administrators access the system
- Regular users are registered but do not log in


---

**Note**: This is a work-in-progress project created for educational purposes and portfolio.
