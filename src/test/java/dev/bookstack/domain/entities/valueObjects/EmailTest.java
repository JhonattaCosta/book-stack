package dev.bookstack.domain.entities.valueObjects;

import dev.bookstack.domain.exceptions.EmailInvalidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;

public class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "teste@teste.com",
            "teste@teste.com.br",
            "teste.teste@dd.com.br",
            "teste@teste.teste.com.br"
    })
    void shouldCreateEmail_WhenIsValid(String validValue){
        Email email = new Email(validValue);
        assertThat(email.getValue()).isEqualTo(validValue);
    }

    @Test
    void shouldThrowException_WhenEmailIsNull(){
        assertThatThrownBy(()->{
            new Email(null);
        }).isInstanceOf(EmailInvalidException.class)
                .hasMessage("Email cannot be null or empty!");
    }

    @Test
    void shouldThrowException_WhenEmailIsEmpty(){
        assertThatThrownBy(()->{
            new Email(" ");
        }).isInstanceOf(EmailInvalidException.class)
                .hasMessage("Email cannot be null or empty!");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        " teste@teste.com ",
            " TESTE@TESTE.COM "
    })
    void shouldRemoveSpacesAndConvertToLowercase(String validEmail){
        Email email = new Email(validEmail);

        assertThat(email.getValue()).isEqualTo("teste@teste.com");
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "testeteste.com",
            "teste@teste",
            "teste@.com",
            "teste!teste@teste.teste.com.br",
            ".teste@teste.com.br",
            " teste@teste.com.br.",
            "teste..teste@teste.com"
    })
    void shouldThrowException_WhenEmailIsInvalid(String invalidValue){
        assertThatThrownBy(()->{
            new Email(invalidValue);
        }).isInstanceOf(EmailInvalidException.class)
                .hasMessage("Email is invalid, please try again.");
    }


}
