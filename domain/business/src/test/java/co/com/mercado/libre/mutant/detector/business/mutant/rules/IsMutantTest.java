package co.com.mercado.libre.mutant.detector.business.mutant.rules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class IsMutantTest {

    @InjectMocks
    private IsMutant isMutant;

    private List<String> mutantDna;
    private List<String> humanDna;
    private List<String> mutantDnaTwo;
    private List<String> mutantDnaThree;
    private List<String> mutantDnaFour;
    private List<String> mutantDnaFive;
    private List<String> mutantDnaSix;
    private List<String> mutantDnaSeven;
    private List<String> mutantDnaEight;
    private List<String> mutantDnaNine;
    private List<String> mutantDnaTen;

    @BeforeEach
    void setUp() {
        mutantDna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        mutantDnaTwo = Arrays.asList("ATAAAA","CAGTGC","TAATGT","AGAAGG","TTGCTA","TCACTG");
        mutantDnaThree = Arrays.asList("ATAAGA","CAGTGC","GGGGGT","AGAAGG","TTGCTA","TCACTG");
        mutantDnaFour = Arrays.asList("ATAAGA","CAGTGC","GGCCGT","ATTTTG","TTGCTA","TCACTG");
        mutantDnaFive = Arrays.asList("ATAAGA","CAGTGC","GGACGT","ATTATG","TTGCTA","TCACTG");
        mutantDnaSix = Arrays.asList("ATAAGA","CAGTGC","GGGCGT","ATTGTG","TTGCGA","TCACTG");
        mutantDnaSeven = Arrays.asList("AGTC", "ACTG", "CATG", "GTTA");
        mutantDnaEight = Arrays.asList("AGTC", "AGTG", "CGCG", "GGTA");
        mutantDnaNine = Arrays.asList("ATAAGC", "CAGTAC", "GGGAGT", "ATACTG", "TAGCGA", "TCACTT");
        mutantDnaTen = Arrays.asList("AGAAGC", "CAGTAC", "GGCGGT", "ATACGG", "TAGCGA", "TCACTT");
        humanDna = Arrays.asList("ATGC", "ATGC", "TGAC", "CGTA");
    }

    @Test
    void shouldDetectMutantInDNA() {
        assertTrue(isMutant.evaluate(mutantDna));
    }

    @Test
    void shouldDetectMutantInDNATestTwo() {
        assertTrue(isMutant.evaluate(mutantDnaTwo));
    }

    @Test
    void shouldDetectMutantInDNATestThree() {
        assertTrue(isMutant.evaluate(mutantDnaThree));
    }

    @Test
    void shouldDetectMutantInDNAFour() {
        assertTrue(isMutant.evaluate(mutantDnaFour));
    }

    @Test
    void shouldDetectMutantInDNAFive() {
        assertTrue(isMutant.evaluate(mutantDnaFive));
    }

    @Test
    void shouldDetectMutantInDNASix() {
        assertTrue(isMutant.evaluate(mutantDnaSix));
    }

    @Test
    void shouldDetectMutantInDNASeven() {
        assertTrue(isMutant.evaluate(mutantDnaSeven));
    }

    @Test
    void shouldDetectMutantInDNAEight() {
        assertTrue(isMutant.evaluate(mutantDnaEight));
    }

    @Test
    void shouldDetectMutantInDNANine() {
        assertTrue(isMutant.evaluate(mutantDnaNine));
    }

    @Test
    void shouldDetectMutantInDNATen() {
        assertTrue(isMutant.evaluate(mutantDnaTen));
    }

    @Test
    void shouldDetectHumanInDNA() {
        assertFalse(isMutant.evaluate(humanDna));
    }


}
