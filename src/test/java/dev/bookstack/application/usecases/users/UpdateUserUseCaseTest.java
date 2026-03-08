package dev.bookstack.application.usecases.users;

import dev.bookstack.application.dto.request.UpdateUserRequestDto;
import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.usecases.user.UpdateUserUseCase;
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
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UpdateUserUseCaseTest {

    @Mock
    private UsersRepository repository;

    @InjectMocks
    private UpdateUserUseCase updateUserUseCase;

    String emailString = "test@test.com";
    Email email = new Email(emailString);
    String cpfString = "54647142949";
    Cpf cpf = new Cpf(cpfString);

    @Test
    void shouldUpdatedUserName(){

       Users domaindUser = new Users(1L, "Jhonatta Costa", email, cpf,false,true, LocalDateTime.now(),LocalDateTime.now());
       UpdateUserRequestDto request = new UpdateUserRequestDto("Jhonatta Dos Santos Costa", "test@test.com");
       Users updatedUser = new Users(1L, "Jhonatta Dos Santos Costa", email, cpf,false,true, LocalDateTime.now(),LocalDateTime.now());

       when(repository.findById(1L)).thenReturn(Optional.of(domaindUser));
       when(repository.save(any(Users.class))).thenReturn(updatedUser);

        UserResponseDto result = updateUserUseCase.execute(1L,request);

        verify(repository).save(any(Users.class));
        assertThat(result.name()).isEqualTo(request.name());

    }
    @Test
    void shouldUpdatedUserEmail(){

        Users domaindUser = new Users(1L, "Jhonatta Costa", new Email("tesst@test.com"), cpf,false,true, LocalDateTime.now(),LocalDateTime.now());
        UpdateUserRequestDto request = new UpdateUserRequestDto("Jhonatta Dos Santos Costa", "test@test.com");
        Users updatedUser = new Users(1L, "Jhonatta Dos Santos Costa", new Email("test@test.com"), cpf,false,true, LocalDateTime.now(),LocalDateTime.now());

        when(repository.findById(1L)).thenReturn(Optional.of(domaindUser));
        when(repository.save(any(Users.class))).thenReturn(updatedUser);

        UserResponseDto result = updateUserUseCase.execute(1L,request);

        verify(repository).save(any(Users.class));
        assertThat(result.email()).isEqualTo(request.email());

    }

    @Test
    void shouldThrowException_whenUserNotFound(){
        UpdateUserRequestDto request = new UpdateUserRequestDto("Jhonatta Dos Santos Costa", "test@test.com");
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            updateUserUseCase.execute(1L,request);
        }).isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User not found try again", "USER_NOT_FOUND");
    }

}
