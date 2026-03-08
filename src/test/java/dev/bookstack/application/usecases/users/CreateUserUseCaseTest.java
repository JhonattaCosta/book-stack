package dev.bookstack.application.usecases.users;

import dev.bookstack.application.dto.request.CreateUserRequestDto;
import dev.bookstack.application.dto.response.UserResponseDto;
import dev.bookstack.application.usecases.user.CreateUserUseCase;
import dev.bookstack.domain.entities.Users;
import dev.bookstack.domain.entities.valueObjects.Cpf;
import dev.bookstack.domain.entities.valueObjects.Email;
import dev.bookstack.domain.exceptions.CpfAlreadyExistsException;
import dev.bookstack.domain.exceptions.EmailAlreadyExistsException;
import dev.bookstack.domain.exceptions.UserInvalidException;
import dev.bookstack.domain.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

    @Mock
    private UsersRepository repository;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    String emailString = "test@test.com";
    Email email = new Email(emailString);
    String cpfString = "54647142949";
    Cpf cpf = new Cpf(cpfString);


    @Test
    void shouldCreateUser_Sucessfully(){

        CreateUserRequestDto request = new CreateUserRequestDto("Jhonatta", "test@test.com", "54647142949" );
        Users createdUser = new Users(1L, "Jhonatta", email, cpf,false,true, LocalDateTime.now(),LocalDateTime.now());

        when(repository.existsByEmail(emailString)).thenReturn(false);
        when(repository.existsByCpf(cpfString)).thenReturn(false);
        when(repository.save(any(Users.class))).thenReturn(createdUser);

        UserResponseDto result = createUserUseCase.execute(request);

        verify(repository).save(any(Users.class));
        assertThat(result.id()).isEqualTo(createdUser.getId());
        assertThat(result.name()).isEqualTo(createdUser.getName());
        assertThat(result.email()).isEqualTo(createdUser.getEmail().getValue());
        assertThat(result.cpf()).isEqualTo(createdUser.getCpf().getMaskedCpf());
        assertThat(result.isAdmin()).isEqualTo(false);
        assertThat(result.isActive()).isEqualTo(true);
    }

    @Test
    void shouldThrowException_WhenNameIsNull(){
        CreateUserRequestDto request = new CreateUserRequestDto(null, "test@test.com", "54647142949" );
        assertThatThrownBy(()->{
            createUserUseCase.execute(request);
        }).isInstanceOf(UserInvalidException.class)
                .hasMessageContaining("Name cannot be null or empty!","NAME_NOT_NULL");
    }

    @Test
    void shouldThrowException_WhenNameIsEmpty(){
        CreateUserRequestDto request = new CreateUserRequestDto(" ", "test@test.com", "54647142949" );
        assertThatThrownBy(()->{
            createUserUseCase.execute(request);
        }).isInstanceOf(UserInvalidException.class)
                .hasMessageContaining("Name cannot be null or empty!","NAME_NOT_EMPTY");
    }

    @Test
    void shouldThrowException_WhenEmailExists(){
        CreateUserRequestDto request = new CreateUserRequestDto("Jhonatta", "test@test.com", "54647142949" );
        when(repository.existsByEmail(emailString)).thenReturn(true);
        assertThatThrownBy(()->{
           createUserUseCase.execute(request);
        }).isInstanceOf(EmailAlreadyExistsException.class)
                .hasMessageContaining("Email already exists try again", "EMAIL_ALREADY_EXISTS");
    }

    @Test
    void shouldThrowException_WhenCpfExists(){
        CreateUserRequestDto request = new CreateUserRequestDto("Jhonatta", "test@test.com", "54647142949" );
        when(repository.existsByCpf(cpfString)).thenReturn(true);
        assertThatThrownBy(()->{
            createUserUseCase.execute(request);
        }).isInstanceOf(CpfAlreadyExistsException.class)
                .hasMessageContaining("Cpf already exists try again", "CPF_ALREADY_EXISTS");
    }







}
