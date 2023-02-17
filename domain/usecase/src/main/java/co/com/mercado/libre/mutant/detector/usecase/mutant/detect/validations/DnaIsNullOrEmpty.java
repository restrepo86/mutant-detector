package co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations;

import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.exceptions.EmptyDnaException;
import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;

import java.util.List;

public class DnaIsNullOrEmpty implements IValidator {

    @Override
    public void validate(List<String> dna) {
        if (dna == null || dna.isEmpty()) {
            throw new EmptyDnaException(ApplicationCodesEnum.EMPTY_DNA_EXCEPTION);
        }
    }

}
