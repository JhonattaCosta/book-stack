package dev.bookstack.infrastructure.persistence.mappers;

import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.entities.valueObjects.Cpf;
import dev.bookstack.domain.entities.valueObjects.Email;
import dev.bookstack.infrastructure.persistence.entities.UsersEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


public class UserEntityMapperTest {


    @Test
    void shouldConverterUsers_ToUsersEntity(){
        Users user = new Users(1L, "Jhonatta", new Email("teste@teste.com"), new Cpf("54647142949"), false, true, LocalDateTime.now(), LocalDateTime.now());

        UsersEntity entity = UsersEntityMapper.toEntity(user);


        assertThat(entity.getId()).isEqualTo(user.getId());
        assertThat(entity.getName()).isEqualTo(user.getName());
        assertThat(entity.getEmail()).isEqualTo(user.getEmail().getValue());
        assertThat(entity.getCpf()).isEqualTo(user.getCpf().getValue());
        assertThat(entity.isAdmin()).isEqualTo(user.isAdmin());
        assertThat(entity.isActive()).isEqualTo(user.isActive());
        assertThat(entity.getCreatedAt()).isEqualTo(user.getCreatedAt());
        assertThat(entity.getUpdatedAt()).isEqualTo(user.getUpdatedAt());
    }

    @Test
    void shouldConverterUsers_ToUsersDomain(){

        UsersEntity entity = new UsersEntity(1L, "Jhonatta", "teste@teste.com", "54647142949", false, true, LocalDateTime.now(), LocalDateTime.now());


        Users user = UsersEntityMapper.toDomain(entity);


        assertThat(user.getId()).isEqualTo(entity.getId());
        assertThat(user.getName()).isEqualTo(entity.getName());
        assertThat(user.getEmail().getValue()).isEqualTo(entity.getEmail());
        assertThat(user.getCpf().getValue()).isEqualTo(entity.getCpf());
        assertThat(user.isAdmin()).isEqualTo(entity.isAdmin());
        assertThat(user.isActive()).isEqualTo(entity.isActive());
        assertThat(user.getCreatedAt()).isEqualTo(entity.getCreatedAt());
        assertThat(user.getUpdatedAt()).isEqualTo(entity.getUpdatedAt());
    }



}
