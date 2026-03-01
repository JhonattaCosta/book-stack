package dev.bookstack.domain.entities;

import dev.bookstack.domain.entities.valueObjects.Cpf;
import dev.bookstack.domain.entities.valueObjects.Email;
import dev.bookstack.domain.exceptions.UserInvalidException;

import java.time.LocalDateTime;

public class Users {

    private final Long id;
    private final String name;
    private final Email email;
    private final Cpf cpf;
    private final boolean isAdmin;
    private final boolean isActive;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Users(Long id, String name, Email email, Cpf cpf, boolean isAdmin, boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (name == null || name.isBlank()) {
            throw new UserInvalidException("Name cannot be null or empty!","NAME_NOT_NULL");
        }
        if (createdAt == null) {
            throw new UserInvalidException("Created At cannot be null!","CREATED_AT_NOT_NULL");
        }
        if (updatedAt == null) {
            throw new UserInvalidException("Updated At cannot be null!","UPDATED_AT_NOT_NULL");
        }


        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
