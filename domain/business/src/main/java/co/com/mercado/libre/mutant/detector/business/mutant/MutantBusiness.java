package co.com.mercado.libre.mutant.detector.business.mutant;

import co.com.mercado.libre.mutant.detector.business.mutant.rules.IsMutant;
import co.com.mercado.libre.mutant.detector.model.mutant.dto.Dna;
import co.com.mercado.libre.mutant.detector.model.mutant.gateways.IMutantBusinessGateway;
import reactor.core.publisher.Mono;

public class MutantBusiness implements IMutantBusinessGateway {

    private final IsMutant isMutant;

    public MutantBusiness(IsMutant isMutant) {
        this.isMutant = isMutant;
    }

    @Override
    public Mono<Boolean> executeBusinessRules(Dna dna) {
        return Mono.just(isMutant.evaluate(dna.getDna()));
    }



}
