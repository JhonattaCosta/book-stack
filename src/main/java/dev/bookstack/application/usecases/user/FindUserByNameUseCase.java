package dev.bookstack.application.usecases.user;

import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.mappers.UserMapper;
import dev.bookstack.domain.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindUserByNameUseCase {

    private final UsersRepository repository;

    public FindUserByNameUseCase(UsersRepository repository) {
        this.repository = repository;
    }

    public List<UserResponseDto> execute(String name){
        return repository.findByName(name).stream().map(UserMapper::toResponse).toList();
    }
}
