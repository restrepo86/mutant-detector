package co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.mappers;

import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.entities.MutantDetectorHistoryEntity;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.MutantDetectorDTO;

public interface IMutantDetectorHistoryEntityMapper {
    MutantDetectorHistoryEntity toData(MutantDetectorDTO mutantDetectorDTO);
    MutantDetectorDTO toDomain(MutantDetectorHistoryEntity mutantDetectorHistoryEntity);
}
