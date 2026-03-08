package dev.bookstack.application.usecases.users;

import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.usecases.user.FindUserByIdUseCase;
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
public class FindUserByIdUseCaseTest {

    @Mock
    private UsersRepository repository;

    @InjectMocks
    private FindUserByIdUseCase findUserByIdUseCase;

    String emailString = "test@test.com";
    Email email = new Email(emailString);
    String cpfString = "54647142949";
    Cpf cpf = new Cpf(cpfString);

    @Test
    void shouldFindUserByIdWithSucessfully(){

        Optional<Users> userDomain = Optional.of(new Users(1L, "Jhonatta", email, cpf,false,true, LocalDateTime.now(),LocalDateTime.now()));
        Optional<UserResponseDto> userResponse = Optional.of(new UserResponseDto(1L, "Jhonatta", emailString, cpfString,false,true, LocalDateTime.now(),LocalDateTime.now()));

        when(repository.findById(1L)).thenReturn(userDomain);

        UserResponseDto result = findUserByIdUseCase.execute(1L);

        verify(repository).findById(1L);
        assertThat(result.id()).isEqualTo(userResponse.get().id());
        assertThat(result.name()).isEqualTo(userResponse.get().name());


    }

    @Test
    void shouldThrowException_whenIdNotExists(){
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            findUserByIdUseCase.execute(1L);
        }).isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User not found try again!", "USER_NOT_FOUND");
    }




}
