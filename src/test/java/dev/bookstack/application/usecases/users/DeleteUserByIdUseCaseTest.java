package dev.bookstack.application.usecases.users;

import dev.bookstack.application.usecases.user.DeleteUserByIdUseCase;
import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.entities.valueObjects.Cpf;
import dev.bookstack.domain.entities.valueObjects.Email;
import dev.bookstack.domain.exceptions.UserNotFoundException;
import dev.bookstack.domain.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DeleteUserByIdUseCaseTest {

    @Mock
    private UsersRepository repository;

    @InjectMocks
    private DeleteUserByIdUseCase deleteUserByIdUseCase;

    String emailString = "test@test.com";
    Email email = new Email(emailString);
    String cpfString = "54647142949";
    Cpf cpf = new Cpf(cpfString);


    @Test
    void shouldDeleteUserById(){
        Users userDomain = new Users(1L, "Jhonatta", email, cpf,false,true, LocalDateTime.now(),LocalDateTime.now());

        when(repository.findById(1L)).thenReturn(Optional.of(userDomain));

        deleteUserByIdUseCase.execute(1L);

        verify(repository).delete(1L);

    }

    @Test
    void shouldThrowException_whenIdNotExists(){
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            deleteUserByIdUseCase.execute(1L);
        }).isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User not found", "USER_NOT_FOUND");
    }

}
