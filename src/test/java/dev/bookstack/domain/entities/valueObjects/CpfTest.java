package dev.bookstack.domain.entities.valueObjects;

import dev.bookstack.domain.exceptions.CpfInvalidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;

public class CpfTest {


    @ParameterizedTest
    @ValueSource(strings = {
            "54647142949","12345678909"
    })
    void shouldCreateCpf_WhenIsValid(String validValue){
        Cpf cpf = new Cpf(validValue);
        assertThat(cpf.getValue()).isEqualTo(validValue);
    }

    @Test
    void shouldReturnCpfFormatted(){
        String cpfValid = "546.471.429-49";
        Cpf cpf = new Cpf(cpfValid);

        assertThat(cpf.getFormattedValue()).isEqualTo(cpfValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1234567890",
            "123456789000",
            "123456789"
    })
    void shouldThrowException_WhenQuantityOfNumbersIsInsufficient(String cpfInsufficient){
        assertThatThrownBy(()-> {
            new Cpf(cpfInsufficient);
        }).isInstanceOf(CpfInvalidException.class)
                .hasMessage("CPF must contain exactly 11 digits !");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "11111111112"
    })
    void shouldThrowException_WhenCheckCpfIsInvalid(String cpfInvalid){
        assertThatThrownBy(()-> {
            new Cpf(cpfInvalid);
        }).isInstanceOf(CpfInvalidException.class)
                .hasMessage("CPF Invalid!");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "11111111111"
    })
    void shouldThrowException_WhenHaveAllDigitsSame(String cpfInvalid){
        assertThatThrownBy(()-> {
            new Cpf(cpfInvalid);
        }).isInstanceOf(CpfInvalidException.class)
                .hasMessage("CPF cannot have all digits the same!");
    }

    @Test
    void shouldThrowException_WhenCpfIsNull(){
        assertThatThrownBy(()-> {
            new Cpf(null);
        }).isInstanceOf(CpfInvalidException.class)
                .hasMessage("CPF cannot be null or empty!");
    }

    @Test
    void shouldThrowException_WhenCpfIsBlank(){
        assertThatThrownBy(()-> {
            new Cpf(" ");
        }).isInstanceOf(CpfInvalidException.class)
                .hasMessage("CPF cannot be null or empty!");
    }







}
