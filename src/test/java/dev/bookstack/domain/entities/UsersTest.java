package dev.bookstack.domain.entities;

import dev.bookstack.domain.entities.valueObjects.Cpf;
import dev.bookstack.domain.entities.valueObjects.Email;
import dev.bookstack.domain.exceptions.UserInvalidException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.time.LocalDateTime;

public class UsersTest {

    String cpf = "54647142949";
    String email = "test@test.com";



    @Test
    void shouldCreateUser_WhenAllFieldsAreValid(){
        Users user = new Users(null, "Jhonatta", new Email(email), new Cpf(cpf), false, true, LocalDateTime.now(), LocalDateTime.now());

        assertThat(user.getName()).isEqualTo("Jhonatta");
        assertThat(user.getEmail().getValue()).isEqualTo("test@test.com");
        assertThat(user.getCpf().getValue()).isEqualTo("54647142949");
        assertThat(user.isAdmin()).isEqualTo(false);
        assertThat(user.isActive()).isEqualTo(true);
        assertThat(user.getCreatedAt()).isBefore(LocalDateTime.now().plusSeconds(1));
        assertThat(user.getUpdatedAt()).isEqualTo(user.getCreatedAt());
    }

    @Test
    void shoudThrowException_WhenNameIsNull(){
        assertThatThrownBy(()->{
            new Users(null, null, new Email(email), new Cpf(cpf), false, true, LocalDateTime.now(), LocalDateTime.now());
        }).isInstanceOf(UserInvalidException.class)
                .hasMessage("Name cannot be null or empty!");
    }

    @Test
    void shoudThrowException_WhenNameIsBlank(){
        assertThatThrownBy(()->{
            new Users(null, " ", new Email(email), new Cpf(cpf), false, true, LocalDateTime.now(), LocalDateTime.now());
        }).isInstanceOf(UserInvalidException.class)
                .hasMessage("Name cannot be null or empty!");
    }

    @Test
    void shoudThrowException_WhenCreatedAtIsNull(){
        assertThatThrownBy(()->{
            new Users(null, "Jhonatta", new Email(email), new Cpf(cpf), false, true, null, LocalDateTime.now());
        }).isInstanceOf(UserInvalidException.class)
                .hasMessage("Created At cannot be null!");
    }

    @Test
    void shoudThrowException_WhenUpdatedAtIsNull(){
        assertThatThrownBy(()->{
            new Users(null, "Jhonatta", new Email(email), new Cpf(cpf), false, true, LocalDateTime.now(), null);
        }).isInstanceOf(UserInvalidException.class)
                .hasMessage("Updated At cannot be null!");
    }


}
