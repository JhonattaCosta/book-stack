package dev.bookstack.application.usecases.users;

import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.usecases.user.FindAllUsersUseCase;
import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.entities.valueObjects.Cpf;
import dev.bookstack.domain.entities.valueObjects.Email;
import dev.bookstack.domain.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FindAllUsersUseCaseTest {

    @Mock
    private UsersRepository repository;

    @InjectMocks
    private FindAllUsersUseCase findAllUsersUseCase;

    String emailString = "test@test.com";
    Email email = new Email(emailString);
    String cpfString = "54647142949";
    Cpf cpf = new Cpf(cpfString);
    String emailString1 = "test@test.com";
    Email email1 = new Email(emailString);
    String cpfString1 = "12345678909";
    Cpf cpf1 = new Cpf(cpfString);

    @Test
    void shouldShowAllUsers(){

        List<Users> users = Arrays.asList(
            new Users(1L, "Jhonatta", email, cpf,false,true, LocalDateTime.now(),LocalDateTime.now()),
            new Users(2L,"Lidiane", email1,cpf1,false,true,LocalDateTime.now(),LocalDateTime.now())
        );

        List<UserResponseDto> responseDtoList = Arrays.asList(
                new UserResponseDto(1L, "Jhonatta", emailString, cpfString,false,true, LocalDateTime.now(),LocalDateTime.now()),
                new UserResponseDto(2L,"Lidiane", emailString1,cpfString1,false,true,LocalDateTime.now(),LocalDateTime.now())
        );

        when(repository.findAll()).thenReturn(users);

        List<UserResponseDto> result = findAllUsersUseCase.execute();

        assertThat(result.size()).isEqualTo(responseDtoList.size());
        assertThat(result.get(0).id()).isEqualTo(responseDtoList.get(0).id());




    }

}
