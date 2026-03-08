package dev.bookstack.application.usecases.user;

import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.mappers.UserMapper;
import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.exceptions.UserNotFoundException;
import dev.bookstack.domain.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserByIdUseCase {

    private final UsersRepository repository;

    public FindUserByIdUseCase(UsersRepository repository) {
        this.repository = repository;
    }

    public UserResponseDto execute(Long id){
        Optional<Users> optUser = repository.findById(id);
        Users user = optUser.orElseThrow(()->
                new UserNotFoundException("User not found try again!", "USER_NOT_FOUND")
                );
        return UserMapper.toResponse(user);
    }

}
