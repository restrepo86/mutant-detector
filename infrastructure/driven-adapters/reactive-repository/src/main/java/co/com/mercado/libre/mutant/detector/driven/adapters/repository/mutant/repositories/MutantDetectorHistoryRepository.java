package co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.repositories;


import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.entities.MutantDetectorHistoryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;

public interface MutantDetectorHistoryRepository extends ReactiveCrudRepository<MutantDetectorHistoryEntity, BigInteger> {
    Mono<MutantDetectorHistoryEntity> findByDna(String dna);
    Flux<MutantDetectorHistoryEntity> findByMutant(boolean mutant);
}
