package co.com.mercado.libre.mutantdetector.domain.usecase;

import co.com.mercado.libre.mutantdetector.domain.business.MutantBusiness;

import java.util.List;

public class MutantUseCase {

    private final MutantBusiness mutantBusiness;

    public MutantUseCase(MutantBusiness mutantBusiness) {
        this.mutantBusiness = mutantBusiness;
    }

    public void validateDna(List<String> dna) {
        mutantBusiness.isMutant(dna);
    }
}
