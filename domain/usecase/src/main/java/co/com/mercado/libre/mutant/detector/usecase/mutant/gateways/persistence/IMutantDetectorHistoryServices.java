package co.com.mercado.libre.mutant.detector.usecase.mutant.gateways.persistence;

import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.MutantDetectorDTO;
import reactor.core.publisher.Mono;

public interface IMutantDetectorHistoryServices {
    Mono<MutantDetectorDTO> save(MutantDetectorDTO mutantDetectorDTO);
    Mono<Long> countHumanDna();
    Mono<Long> countMutantDna();

}
