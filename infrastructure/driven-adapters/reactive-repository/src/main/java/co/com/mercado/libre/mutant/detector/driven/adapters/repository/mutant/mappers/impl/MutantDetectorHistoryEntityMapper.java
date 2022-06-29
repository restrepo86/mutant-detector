package co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.mappers.impl;

import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.entities.MutantDetectorHistoryEntity;
import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.mappers.IMutantDetectorHistoryEntityMapper;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.MutantDetectorDTO;

import java.util.Date;

public class MutantDetectorHistoryEntityMapper implements IMutantDetectorHistoryEntityMapper {

    @Override
    public MutantDetectorHistoryEntity toData(MutantDetectorDTO mutantDetectorDTO) {
        return MutantDetectorHistoryEntity.builder()
                .dna(mutantDetectorDTO.getDna())
                .mutant(mutantDetectorDTO.isMutant())
                .creationDate(new Date())
                .build();
    }

    @Override
    public MutantDetectorDTO toDomain(MutantDetectorHistoryEntity mutantDetectorHistoryEntity) {
        return MutantDetectorDTO.builder()
                .id(mutantDetectorHistoryEntity.getId())
                .mutant(mutantDetectorHistoryEntity.getMutant())
                .dna(mutantDetectorHistoryEntity.getDna())
                .build();
    }

}
