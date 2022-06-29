package co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations;


import java.util.ArrayList;
import java.util.List;

public class Validations {

    private static List<IValidator> validations;

    private final DnaIsNullOrEmpty dnaIsNullOrEmpty;
    private final DnaHasInvalidSize dnaHasInvalidSize;
    private final DnaHasInvalidNitrogenBase dnaHasInvalidNitrogenBase;

    public Validations(DnaIsNullOrEmpty dnaIsNullOrEmpty, DnaHasInvalidSize dnaHasInvalidSize, DnaHasInvalidNitrogenBase dnaHasInvalidNitrogenBase) {
        this.dnaIsNullOrEmpty = dnaIsNullOrEmpty;
        this.dnaHasInvalidSize = dnaHasInvalidSize;
        this.dnaHasInvalidNitrogenBase = dnaHasInvalidNitrogenBase;
    }

    public void execute(List<String> dna) {
        List<IValidator> validations = buildValidations();
        for (IValidator validation: validations) {
            validation.validate(dna);
        }
    }

    private List<IValidator> buildValidations() {
        if (validations == null) {
            validations = new ArrayList<>();
            validations.add(dnaIsNullOrEmpty);
            validations.add(dnaHasInvalidSize);
            validations.add(dnaHasInvalidNitrogenBase);
        }
        return validations;
    }

}
