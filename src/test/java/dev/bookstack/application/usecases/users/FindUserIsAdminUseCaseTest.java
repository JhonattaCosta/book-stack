package dev.bookstack.application.usecases.users;

import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.usecases.user.FindUserIsAdminUseCase;
import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.entities.valueObjects.Cpf;
import dev.bookstack.domain.entities.valueObjects.Email;
import dev.bookstack.domain.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindUserIsAdminUseCaseTest {

    @Mock
    private UsersRepository repository;

    @InjectMocks
    private FindUserIsAdminUseCase findUserIsAdminUseCase;

    String emailString = "test@test.com";
    Email email = new Email(emailString);
    String cpfString = "54647142949";
    Cpf cpf = new Cpf(cpfString);
    String emailString1 = "test@test.com";
    Email email1 = new Email(emailString);
    String cpfString1 = "12345678909";
    Cpf cpf1 = new Cpf(cpfString);

    @Test
    void shouldFindUserWhenIsAdmin(){
        List<Users> domainUsers = Arrays.asList(
                new Users(1L, "Jhonatta", email, cpf,true,true, LocalDateTime.now(),LocalDateTime.now()),
                new Users(2L,"Jhony", email1,cpf1,true,true,LocalDateTime.now(),LocalDateTime.now())
        );

        List<UserResponseDto> responseList = Arrays.asList(
                new UserResponseDto(1L, "Jhonatta", emailString, cpfString,true,true, LocalDateTime.now(),LocalDateTime.now()),
                new UserResponseDto(2L,"Jhony", emailString1,cpfString1,true,true,LocalDateTime.now(),LocalDateTime.now())
        );

        when(repository.findByIsAdminTrue()).thenReturn(domainUsers);

        List<UserResponseDto> result = findUserIsAdminUseCase.execute();

        assertThat(result.size()).isEqualTo(responseList.size());
        assertThat(result.get(0).name()).isEqualTo(responseList.get(0).name());

    }

}
