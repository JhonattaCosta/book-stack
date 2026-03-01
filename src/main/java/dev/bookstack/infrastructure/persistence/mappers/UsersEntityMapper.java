package dev.bookstack.infrastructure.persistence.mappers;

import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.entities.valueObjects.Cpf;
import dev.bookstack.domain.entities.valueObjects.Email;
import dev.bookstack.infrastructure.persistence.entities.UsersEntity;

public class UsersEntityMapper {


    public static UsersEntity toEntity(Users users){
        UsersEntity entity = new UsersEntity();

        entity.setId(users.getId());
        entity.setName(users.getName());
        entity.setEmail(users.getEmail().getValue());
        entity.setCpf(users.getCpf().getValue());
        entity.setAdmin(users.isAdmin());
        entity.setActive(users.isActive());
        entity.setCreatedAt(users.getCreatedAt());
        entity.setUpdatedAt(users.getUpdatedAt());

        return entity;
    }

    public static Users toDomain(UsersEntity entity){
        Users user = new Users(
                entity.getId(),
                entity.getName(),
                new Email(entity.getEmail()),
                new Cpf(entity.getCpf()),
                entity.isAdmin(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );


        return user;
    }


}
