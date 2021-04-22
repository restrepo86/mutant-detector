package co.com.mercado.libre.mutantdetector.domain.validations;

import co.com.mercado.libre.mutantdetector.domain.exceptions.InvalidNitrogenBaseException;
import co.com.mercado.libre.mutantdetector.domain.exceptions.InvalidRequestException;

import java.util.List;
import java.util.regex.Pattern;

public class DnaHasInvalidNitrogenBase implements IValidator {
    @Override
    public void validate(List<String> dna) throws InvalidRequestException {
        if (dna.stream().anyMatch(dnaRow -> Pattern.compile("[^AGTC]").matcher(dnaRow).find())) {
            throw new InvalidNitrogenBaseException("The Nitrogen base it must be A, G, T, C, in capital letters");
        }
    }

}
