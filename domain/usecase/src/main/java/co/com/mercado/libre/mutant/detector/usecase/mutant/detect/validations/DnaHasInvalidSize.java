package co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations;

import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.exceptions.InvalidSizeDnaException;
import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;

import java.util.List;

public class DnaHasInvalidSize implements IValidator {

    @Override
    public void validate(List<String> dna) {
        if (dna.stream().anyMatch(dnaRow -> dnaRow.length() != dna.size()) || dna.size() < 4) {
            throw new InvalidSizeDnaException(ApplicationCodesEnum.INVALID_SIZE_DNA_EXCEPTION);
        }
    }
}
