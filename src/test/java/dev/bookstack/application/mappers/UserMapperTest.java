package dev.bookstack.application.mappers;

import dev.bookstack.application.dto.request.CreateUserRequestDto;
import dev.bookstack.application.dto.request.UpdateUserRequestDto;
import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.entities.valueObjects.Cpf;
import dev.bookstack.domain.entities.valueObjects.Email;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest {

    @Test
    void shouldConverterCreateUserRequest_ToUser(){

        CreateUserRequestDto request = new CreateUserRequestDto("Jhonatta", "teste@teste.com","54647142949");

        Users user = UserMapper.toDomain(request);

        assertThat(user.getName()).isEqualTo(request.name());
        assertThat(user.getEmail().getValue()).isEqualTo(request.email());
        assertThat(user.getCpf().getValue()).isEqualTo(request.cpf());

    }

    @Test
    void shouldConverterUpdateUserRequest_ToUser(){

        UpdateUserRequestDto request = new UpdateUserRequestDto("Jhonatta", "teste@teste.com");

        Users userUpdated = UserMapper.toUpdateDomain(request);

        assertThat(userUpdated.getName()).isEqualTo(request.name());
        assertThat(userUpdated.getEmail().getValue()).isEqualTo(request.email());

    }

    @Test
    void shouldConverterUser_ToUserResponseDto(){

        String cpf = "54647142949";
        String email = "test@test.com";

        Users user = new Users(null, "Jhonatta", new Email(email), new Cpf(cpf), false, true, LocalDateTime.now(), LocalDateTime.now());

        UserResponseDto response = UserMapper.toResponse(user);


        assertThat(response.id()).isEqualTo(user.getId());
        assertThat(response.name()).isEqualTo(user.getName());
        assertThat(response.email()).isEqualTo(user.getEmail().getValue());
        assertThat(response.cpf()).isEqualTo(user.getCpf().getMaskedCpf());
        assertThat(response.isAdmin()).isEqualTo(user.isAdmin());
        assertThat(response.isActive()).isEqualTo(user.isActive());

    }


}
