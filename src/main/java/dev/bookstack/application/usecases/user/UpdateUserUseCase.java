package dev.bookstack.application.usecases.user;

import dev.bookstack.application.dto.request.UpdateUserRequestDto;
import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.mappers.UserMapper;
import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.entities.valueObjects.Email;
import dev.bookstack.domain.exceptions.UserNotFoundException;
import dev.bookstack.domain.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UpdateUserUseCase {

    private final UsersRepository repository;

    public UpdateUserUseCase(UsersRepository repository) {
        this.repository = repository;
    }

    public UserResponseDto execute(Long id, UpdateUserRequestDto request){
        Users user = repository.findById(id).orElseThrow(()->
                new UserNotFoundException("User not found try again", "USER_NOT_FOUND")
        );
        Users updatedUser = new Users(
                user.getId(),
                request.name() != null && !request.name().isBlank() ? request.name() : user.getName(),
                request.email() != null && !request.name().isBlank() ? new Email(request.email()) : user.getEmail(),
                user.getCpf(),
                false,
                true,
                user.getCreatedAt(),
                LocalDateTime.now()
        );

        return UserMapper.toResponse(repository.save(updatedUser));
    }
}
