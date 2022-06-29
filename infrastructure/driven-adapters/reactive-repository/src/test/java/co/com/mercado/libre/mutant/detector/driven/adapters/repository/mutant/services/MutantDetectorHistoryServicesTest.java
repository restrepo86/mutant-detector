package co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.services;

import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.entities.MutantDetectorHistoryEntity;
import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.mappers.impl.MutantDetectorHistoryEntityMapper;
import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.repositories.MutantDetectorHistoryRepository;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.MutantDetectorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MutantDetectorHistoryServicesTest {

    @InjectMocks
    private MutantDetectorHistoryServices mutantDetectorHistoryServices;

    @Mock
    private MutantDetectorHistoryRepository mutantDetectorHistoryRepository;

    @Mock
    private MutantDetectorHistoryEntityMapper mutantDetectorHistoryEntityMapper;

    private MutantDetectorHistoryEntity mutantDetectorHistoryEntity;
    private MutantDetectorDTO mutantDetectorDTO;

    @BeforeEach
    void setUp() {
        mutantDetectorHistoryEntity = MutantDetectorHistoryEntity.builder()
                .mutant(false)
                .dna("{“dna”:[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}")
                .build();
        mutantDetectorDTO = MutantDetectorDTO.builder()
                .dna(mutantDetectorHistoryEntity.getDna())
                .mutant(mutantDetectorHistoryEntity.getMutant())
                .build();
    }

    @Test
    void shouldSaveMutantData() {
        when(mutantDetectorHistoryRepository.findByDna(mutantDetectorDTO.getDna())).thenReturn(Mono.empty());
        when(mutantDetectorHistoryEntityMapper.toDomain(mutantDetectorHistoryEntity)).thenReturn(mutantDetectorDTO);
        when(mutantDetectorHistoryEntityMapper.toData(mutantDetectorDTO)).thenReturn(mutantDetectorHistoryEntity);
        when(mutantDetectorHistoryRepository.save(any())).thenReturn(Mono.just(mutantDetectorHistoryEntity));
        StepVerifier
                .create(mutantDetectorHistoryServices.save(mutantDetectorDTO))
                .expectNext(mutantDetectorDTO)
                .verifyComplete();
        verify(mutantDetectorHistoryRepository).findByDna(any());
        verify(mutantDetectorHistoryRepository, times(1)).save(any(MutantDetectorHistoryEntity.class));
        verify(mutantDetectorHistoryEntityMapper).toDomain(mutantDetectorHistoryEntity);
        verify(mutantDetectorHistoryEntityMapper).toData(mutantDetectorDTO);
    }

    @Test
    void shouldSaveMutantDataTestNoSaveIfExist() {
        when(mutantDetectorHistoryRepository.findByDna(mutantDetectorDTO.getDna())).thenReturn(Mono.just(mutantDetectorHistoryEntity));
        when(mutantDetectorHistoryEntityMapper.toDomain(mutantDetectorHistoryEntity)).thenReturn(mutantDetectorDTO);
        StepVerifier
                .create(mutantDetectorHistoryServices.save(mutantDetectorDTO))
                .expectNext(mutantDetectorDTO)
                .verifyComplete();
        verify(mutantDetectorHistoryRepository).findByDna(any());
        verify(mutantDetectorHistoryRepository, times(0)).save(any(MutantDetectorHistoryEntity.class));
        verify(mutantDetectorHistoryEntityMapper).toDomain(mutantDetectorHistoryEntity);
    }



}
