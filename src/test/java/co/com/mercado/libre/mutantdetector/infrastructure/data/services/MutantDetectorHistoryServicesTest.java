package co.com.mercado.libre.mutantdetector.infrastructure.data.services;

import co.com.mercado.libre.mutantdetector.domain.dto.MutanDetectorDTO;
import co.com.mercado.libre.mutantdetector.infrastructure.data.entities.MutantDetectorHistoryEntity;
import co.com.mercado.libre.mutantdetector.infrastructure.data.repository.MutantDetectorHistoryRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(MockitoJUnitRunner.class)
public class MutantDetectorHistoryServicesTest {

    @InjectMocks
    private MutantDetectorHistoryServices mutantDetectorHistoryServices;

    @Mock
    private MutantDetectorHistoryRepository mutantDetectorHistoryRepository;

    private MutantDetectorHistoryEntity mutantDetectorHistoryEntity;

    @BeforeEach
    public void setUp() {
        mutantDetectorHistoryEntity = MutantDetectorHistoryEntity.builder()
                .mutant(false)
                .id(BigInteger.ONE)
                .dna("{“dna”:[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}")
                .build();
    }

    @Test
    public void shouldSaveMutantData() {
        when(mutantDetectorHistoryRepository.findByDna(any())).thenReturn(Optional.ofNullable(mutantDetectorHistoryEntity));
        mutantDetectorHistoryServices.save(MutanDetectorDTO.builder().build());
        verify(mutantDetectorHistoryRepository).findByDna(any());
        verify(mutantDetectorHistoryRepository, times(0)).save(any(MutantDetectorHistoryEntity.class));
    }



}
