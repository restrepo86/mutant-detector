package co.com.mercado.libre.mutantdetector.domain.validations;

import co.com.mercado.libre.mutantdetector.domain.exceptions.EmptyDnaException;
import co.com.mercado.libre.mutantdetector.domain.exceptions.InvalidRequestException;

import java.util.List;

public class DnaIsNullOrEmpty implements IValidator {

    @Override
    public void validate(List<String> dna) throws InvalidRequestException {
        if (dna == null || dna.isEmpty()) {
            throw new EmptyDnaException("The DNA is empty");
        }
    }

}
