package co.com.mercado.libre.mutantdetector.infrastructure.data.services;

import co.com.mercado.libre.mutantdetector.domain.dto.MutanDetectorDTO;
import co.com.mercado.libre.mutantdetector.infrastructure.data.entities.MutantDetectorHistoryEntity;
import co.com.mercado.libre.mutantdetector.infrastructure.data.repository.MutantDetectorHistoryRepository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Date;

public class MutantDetectorHistoryServices {

    private final MutantDetectorHistoryRepository mutantDetectorHistoryRepository;

    public MutantDetectorHistoryServices(MutantDetectorHistoryRepository mutantDetectorHistoryRepository) {
        this.mutantDetectorHistoryRepository = mutantDetectorHistoryRepository;
    }

    public Mono<MutanDetectorDTO> save(MutanDetectorDTO mutanDetectorDTO) {
        return Mono.justOrEmpty(mutantDetectorHistoryRepository.findByDna(mutanDetectorDTO.getDna())
                .map(this::toDomain)
                .orElseGet(() -> toDomain(mutantDetectorHistoryRepository.save(toData(mutanDetectorDTO)))));
    }

    private MutantDetectorHistoryEntity toData(MutanDetectorDTO mutanDetectorDTO) {
        return MutantDetectorHistoryEntity.builder()
                .dna(mutanDetectorDTO.getDna())
                .mutant(mutanDetectorDTO.isMutant())
                .creationDate(new Date())
                .build();
    }

    private MutanDetectorDTO toDomain(MutantDetectorHistoryEntity mutantDetectorHistoryEntity) {
        return MutanDetectorDTO.builder()
                .id(mutantDetectorHistoryEntity.getId())
                .mutant(mutantDetectorHistoryEntity.getMutant())
                .dna(mutantDetectorHistoryEntity.getDna())
                .build();
    }

    @Transactional(readOnly = true)
    public Long countHumanDna() {
        return getCountMutant(false);
    }

    private Long getCountMutant(boolean mutant) {
        return mutantDetectorHistoryRepository.findByMutant(mutant)
                .stream().count();
    }

    @Transactional(readOnly = true)
    public Long countMutantDna() {
        return getCountMutant(true);
    }
}
