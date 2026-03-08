package dev.bookstack.application.usecases.user;

import dev.bookstack.application.dto.response.UserResponseDto;
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
public class FindUserByEmailUseCaseTest {

    @Mock
    private UsersRepository repository;

    @InjectMocks
    private FindUserByEmailUseCase findUserByEmailUseCase;

    String emailString = "test@test.com";
    Email email = new Email(emailString);
    String cpfString = "54647142949";
    Cpf cpf = new Cpf(cpfString);

    @Test
    void shouldFindUserByEmail(){
        Optional<UserResponseDto> request = Optional.of(new UserResponseDto(1L, "Jhonatta", emailString, cpfString,false,true, LocalDateTime.now(),LocalDateTime.now()));
        Optional<Users> domainUser = Optional.of(new Users(1L, "Jhonatta", email, cpf,false,true, LocalDateTime.now(),LocalDateTime.now()));

        when(repository.findByEmail(emailString)).thenReturn(domainUser);

        UserResponseDto result = findUserByEmailUseCase.execute(emailString);

        verify(repository).findByEmail(emailString);
        assertThat(result.id()).isEqualTo(request.get().id());
        assertThat(result.name()).isEqualTo(request.get().name());

    }

    @Test
    void shouldThrowException_whenEmailNotExists(){
        when(repository.findByEmail(emailString)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            findUserByEmailUseCase.execute(emailString);
        }).isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User not found try again", "USER_NOT_FOUND");
    }



}
