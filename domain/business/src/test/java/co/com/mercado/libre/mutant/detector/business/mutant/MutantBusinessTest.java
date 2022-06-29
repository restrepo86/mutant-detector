package co.com.mercado.libre.mutant.detector.business.mutant;

import co.com.mercado.libre.mutant.detector.business.mutant.rules.IsMutant;
import co.com.mercado.libre.mutant.detector.model.mutant.dto.Dna;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MutantBusinessTest {

    @InjectMocks
    private MutantBusiness mutantBusiness;

    @Mock
    private IsMutant isMutant;

    private Dna dna;

    @BeforeEach
    void setUp() {
        dna = Dna.builder()
                .dna(Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"))
                .build();
    }

    @Test
    void shouldTestBusinessRules() {
        when(isMutant.evaluate(dna.getDna())).thenReturn(true);
        StepVerifier
                .create(mutantBusiness.executeBusinessRules(dna))
                .expectNext(Boolean.TRUE)
                .expectComplete()
                .verify();
        verify(isMutant).evaluate(dna.getDna());
    }

    @Test
    void shouldTestBusinessRulesTwo() {
        when(isMutant.evaluate(dna.getDna())).thenReturn(false);
        StepVerifier
                .create(mutantBusiness.executeBusinessRules(dna))
                .expectNext(Boolean.FALSE)
                .expectComplete()
                .verify();
        verify(isMutant).evaluate(dna.getDna());
    }

}
