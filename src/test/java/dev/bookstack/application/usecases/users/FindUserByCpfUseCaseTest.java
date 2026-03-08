package dev.bookstack.application.usecases.users;

import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.usecases.user.FindUserByCpfUseCase;
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

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindUserByCpfUseCaseTest {

    @Mock
    private UsersRepository repository;

    @InjectMocks
    private FindUserByCpfUseCase findUserByCpfUseCase;

    String emailString = "test@test.com";
    Email email = new Email(emailString);
    String cpfString = "54647142949";
    Cpf cpf = new Cpf(cpfString);

    @Test
    void shouldFindUserByCpf(){
        Optional<UserResponseDto> request = Optional.of(new UserResponseDto(1L, "Jhonatta", emailString, cpfString,false,true, LocalDateTime.now(),LocalDateTime.now()));
        Optional<Users> domainUser = Optional.of(new Users(1L, "Jhonatta", email, cpf,false,true, LocalDateTime.now(),LocalDateTime.now()));

        when(repository.findByCpf(cpfString)).thenReturn(domainUser);

        UserResponseDto result = findUserByCpfUseCase.execute(cpfString);

        verify(repository).findByCpf(cpfString);
        assertThat(result.id()).isEqualTo(request.get().id());
        assertThat(result.name()).isEqualTo(request.get().name());

    }

    @Test
    void shouldThrowException_whenCpfNotExists(){
        when(repository.findByCpf(cpfString)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            findUserByCpfUseCase.execute(cpfString);
        }).isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User not found try again", "USER_NOT_FOUND");
    }
}
