package co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.services;

import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.mappers.IMutantDetectorHistoryEntityMapper;
import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.mappers.impl.MutantDetectorHistoryEntityMapper;
import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.repositories.MutantDetectorHistoryRepository;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.MutantDetectorDTO;
import co.com.mercado.libre.mutant.detector.usecase.mutant.gateways.persistence.IMutantDetectorHistoryServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class MutantDetectorHistoryServices implements IMutantDetectorHistoryServices {

    private final MutantDetectorHistoryRepository mutantDetectorHistoryRepository;
    private final IMutantDetectorHistoryEntityMapper mutantDetectorHistoryMapper;

    public MutantDetectorHistoryServices(MutantDetectorHistoryRepository mutantDetectorHistoryRepository, MutantDetectorHistoryEntityMapper mutantDetectorHistoryMapper) {
        this.mutantDetectorHistoryRepository = mutantDetectorHistoryRepository;
        this.mutantDetectorHistoryMapper = mutantDetectorHistoryMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Mono<MutantDetectorDTO> save(MutantDetectorDTO mutantDetectorDTO) {
        return mutantDetectorHistoryRepository.findByDna(mutantDetectorDTO.getDna())
                .switchIfEmpty(Mono.defer(
                        () -> mutantDetectorHistoryRepository.save(mutantDetectorHistoryMapper.toData(mutantDetectorDTO))))
                .map(mutantDetectorHistoryMapper::toDomain);
    }

    @Override
    public Mono<Long> countHumanDna() {
        return getCountMutant(false);
    }

    @Override
    public Mono<Long> countMutantDna() {
        return getCountMutant(true);
    }

    @Transactional(readOnly = true)
    private Mono<Long> getCountMutant(boolean mutant) {
        return mutantDetectorHistoryRepository.findByMutant(mutant)
                .count();
    }
}
