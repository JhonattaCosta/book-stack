package dev.bookstack.application.mappers;



import dev.bookstack.application.dto.request.CreateUserRequestDto;
import dev.bookstack.application.dto.request.UpdateUserRequestDto;
import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.entities.valueObjects.Cpf;
import dev.bookstack.domain.entities.valueObjects.Email;

import java.time.LocalDateTime;

public class UserMapper {

    public static Users toDomain(CreateUserRequestDto request) {
        Users user = new Users(
                null,
                request.name(),
                new Email(request.email()),
                new Cpf(request.cpf()),
                false,
                true,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return user;
    }

    public static Users toUpdateDomain(UpdateUserRequestDto request){
        Users user = new Users(
                null,
                request.name(),
                new Email(request.email()),
                null,
                false,
                true,
                null,
                LocalDateTime.now()
        );
        return user;
    }

    public static UserResponseDto toResponse(Users user){
        UserResponseDto response = new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail().getValue(),
                user.getCpf().getMaskedCpf(),
                user.isAdmin(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

        return response;
    }



}
