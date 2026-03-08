package dev.bookstack.application.usecases.user;

import dev.bookstack.domain.exceptions.UserNotFoundException;
import dev.bookstack.domain.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserByIdUseCase {

    private final UsersRepository repository;

    public DeleteUserByIdUseCase(UsersRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        repository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found", "USER_NOT_FOUND"));

        repository.delete(id);
    }
}
