package dev.bookstack.domain.entities.valueObjects;

import dev.bookstack.domain.exceptions.CpfInvalidException;

import java.util.regex.Pattern;

public class Cpf {

    private final String value;


    public Cpf(String value) {
        if (value == null || value.isBlank()){
            throw new CpfInvalidException("CPF cannot be null or empty!", "CPF_NULL_OR_EMPTY");
        }
        value = value.replaceAll("[^0-9]", "");
        if (value.length() != 11){
            throw new CpfInvalidException("CPF must contain exactly 11 digits !", "CPF_INVALID_FORMAT");
        }
        if (value.matches("^(\\d)\\1{10}$")){
            throw new CpfInvalidException("CPF cannot have all digits the same!", "CPF_NOT_HAVE_ALL_DIGITS_SAME");
        }

        validateCpf(value);


        this.value = value;
    }

    private void validateCpf(String cpf){

        int sum = 0;
        int weight = 10;
        for(int i = 0; i < 9; i++){
            int firstDigitVerifier = Character.getNumericValue(cpf.charAt(i));
            sum += weight * firstDigitVerifier;
            weight--;
        }
        int resultFirstDigitVerifier = sum % 11;
        int calculateFirstDigit = resultFirstDigitVerifier < 2 ? 0 : 11 - resultFirstDigitVerifier;

        sum = 0;
        weight = 11;
        for(int i = 0; i < 10; i++){
            int secondDigitVerifier = Character.getNumericValue(cpf.charAt(i));
            sum += weight * secondDigitVerifier;
            weight--;
        }
        int resultSecondDigitVerifier = sum % 11;
        int calculateSecondDigit = resultSecondDigitVerifier < 2 ? 0 : 11 - resultSecondDigitVerifier;

        if (calculateFirstDigit != Character.getNumericValue(cpf.charAt(9)) || calculateSecondDigit != Character.getNumericValue(cpf.charAt(10))){
            throw new CpfInvalidException("CPF Invalid!", "CPF_INVALID");
        }

    }

    public String getValue() {
        return value;
    }

    public String getFormattedValue() {
        return value.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
