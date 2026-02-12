package dev.bookstack.domain.entities.valueObjects;

import dev.bookstack.domain.exceptions.IsbnInvalidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;

public class IsbnTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "9788535902778","9798590636075"
    })
    void shouldCreateIsbn_WhenIsValid(String validValue){
        Isbn isbn = new Isbn(validValue);
        assertThat(isbn.getValue()).isEqualTo(validValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1238535902778",
            "9778535902778"
    })
    void shouldThrowException_WhenPrefixIsNeither978or979(String invalidPrefix){
        assertThatThrownBy(()->{
            new Isbn(invalidPrefix);
        })
                .isInstanceOf(IsbnInvalidException.class)
                .hasMessageContaining("ISBN must contain exactly 13 digits and start with 978 or 979")
                .extracting("errorCode").isEqualTo("ISBN_INVALID_FORMAT");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "978-85-325-3078-3", // Com hífens agora deve falhar
            "978 85 325 3078 3", // Com espaços agora deve falhar
            "978853253078a"      // Com letras deve falhar
    })
    void shouldThrowException_WhenValueContainsNonDigits(String input) {
        assertThatThrownBy(() -> new Isbn(input))
                .isInstanceOf(IsbnInvalidException.class)
                .hasMessageContaining("must contain exactly 13 digits")
                .extracting("errorCode").isEqualTo("ISBN_INVALID_FORMAT");
    }

    @Test
    void shouldThrowException_WhenCheckSumIsInvalid(){
        String invalidChecksum = "9788535902775";
        assertThatThrownBy(()->{
            new Isbn(invalidChecksum);
        })
                .isInstanceOf(IsbnInvalidException.class)
                .hasMessage("Isbn Invalid!");
    }


    @Test
    void shouldThrowException_WhenIsbnIsNull(){
        assertThatThrownBy(() -> {
            new Isbn(null);
        })
                .isInstanceOf(IsbnInvalidException.class)
                .hasMessage("Isbn cannot be null or empty!");

    }

    @Test
    void shouldThrowException_WhenIsbnIsBlank(){
        assertThatThrownBy(() -> {
            new Isbn(" ");
        })
                .isInstanceOf(IsbnInvalidException.class)
                .hasMessage("Isbn cannot be null or empty!");

    }

}
