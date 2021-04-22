package co.com.mercado.libre.mutantdetector.domain.validations;

import co.com.mercado.libre.mutantdetector.domain.exceptions.InvalidRequestException;
import co.com.mercado.libre.mutantdetector.domain.exceptions.InvalidSizeDnaException;

import java.util.List;

public class DnaHasInvalidSize implements IValidator {

    @Override
    public void validate(List<String> dna) throws InvalidRequestException {
        if (dna.stream().anyMatch(dnaRow -> dnaRow.length() != dna.size()) || dna.size() < 4) {
            throw new InvalidSizeDnaException("The DNA size is invalid, because it must be N x N matrix and N > 3");
        }
    }
}
