package dev.bookstack.application.usecases.user;

import dev.bookstack.application.dto.request.CreateUserRequestDto;
import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.mappers.UserMapper;
import dev.bookstack.domain.exceptions.CpfAlreadyExistsException;
import dev.bookstack.domain.exceptions.EmailAlreadyExistsException;
import dev.bookstack.domain.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UsersRepository repository;

    public CreateUserUseCase(UsersRepository repository) {
        this.repository = repository;
    }

    public UserResponseDto execute(CreateUserRequestDto request){
        if(repository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException("Email already exists try again","EMAIL_ALREADY_EXISTS");
        }
        if(repository.existsByCpf(request.cpf())){
            throw new CpfAlreadyExistsException("Cpf already exists try again", "CPF_ALREADY_EXISTS");
        }

        return UserMapper.toResponse(repository.save(UserMapper.toDomain(request)));
    }
}
