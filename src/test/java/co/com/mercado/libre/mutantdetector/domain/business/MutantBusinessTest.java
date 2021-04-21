package co.com.mercado.libre.mutantdetector.domain.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MutantBusinessTest {

    @InjectMocks
    private MutantBusiness mutantBusiness;

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
    public void setUp() {
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
    public void shouldDetectMutantInDNA() {
        assertTrue(mutantBusiness.isMutant(mutantDna).block());
    }

    @Test
    public void shouldDetectMutantInDNATestTwo() {
        assertTrue(mutantBusiness.isMutant(mutantDnaTwo).block());
    }

    @Test
    public void shouldDetectMutantInDNATestThree() {
        assertTrue(mutantBusiness.isMutant(mutantDnaThree).block());
    }

    @Test
    public void shouldDetectMutantInDNAFour() {
        assertTrue(mutantBusiness.isMutant(mutantDnaFour).block());
    }

    @Test
    public void shouldDetectMutantInDNAFive() {
        assertTrue(mutantBusiness.isMutant(mutantDnaFive).block());
    }

    @Test
    public void shouldDetectMutantInDNASix() {
        assertTrue(mutantBusiness.isMutant(mutantDnaSix).block());
    }

    @Test
    public void shouldDetectMutantInDNASeven() {
        assertTrue(mutantBusiness.isMutant(mutantDnaSeven).block());
    }

    @Test
    public void shouldDetectMutantInDNAEight() {
        assertTrue(mutantBusiness.isMutant(mutantDnaEight).block());
    }

    @Test
    public void shouldDetectMutantInDNANine() {
        assertTrue(mutantBusiness.isMutant(mutantDnaNine).block());
    }

    @Test
    public void shouldDetectMutantInDNATen() {
        assertTrue(mutantBusiness.isMutant(mutantDnaTen).block());
    }

    @Test
    public void shouldDetectHumanInDNA() {
        assertFalse(mutantBusiness.isMutant(humanDna).block());
    }

}
