package co.com.mercado.libre.mutantdetector.domain.validations;

import co.com.mercado.libre.mutantdetector.domain.exceptions.InvalidRequestException;

import java.util.List;

public interface IValidator {

    void validate(List<String> dna) throws InvalidRequestException;

}
