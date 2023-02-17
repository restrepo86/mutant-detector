package co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations;


import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.exceptions.InvalidNitrogenBaseException;
import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;

import java.util.List;
import java.util.regex.Pattern;

public class DnaHasInvalidNitrogenBase implements IValidator {
    @Override
    public void validate(List<String> dna) {
        if (dna.stream().anyMatch(dnaRow -> Pattern.compile("[^AGTC]").matcher(dnaRow).find())) {
            throw new InvalidNitrogenBaseException(ApplicationCodesEnum.INVALID_NITROGEN_BASE_EXCEPTION);
        }
    }

}
