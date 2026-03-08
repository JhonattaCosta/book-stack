package dev.bookstack.application.usecases.user;

import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.mappers.UserMapper;
import dev.bookstack.domain.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUsersUseCase {

    private final UsersRepository repository;

    public FindAllUsersUseCase(UsersRepository repository) {
        this.repository = repository;
    }

    public List<UserResponseDto> execute(){
        return repository.findAll().stream().map(UserMapper::toResponse).toList();
    }

}
