package co.com.mercado.libre.mutantdetector.domain.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MutantBusiness.class })
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
        assertTrue(mutantBusiness.isMutant(mutantDna));
    }

    @Test
    public void shouldDetectMutantInDNATestTwo() {
        assertTrue(mutantBusiness.isMutant(mutantDnaTwo));
    }

    @Test
    public void shouldDetectMutantInDNATestThree() {
        assertTrue(mutantBusiness.isMutant(mutantDnaThree));
    }

    @Test
    public void shouldDetectMutantInDNAFour() {
        assertTrue(mutantBusiness.isMutant(mutantDnaFour));
    }

    @Test
    public void shouldDetectMutantInDNAFive() {
        assertTrue(mutantBusiness.isMutant(mutantDnaFive));
    }

    @Test
    public void shouldDetectMutantInDNASix() {
        assertTrue(mutantBusiness.isMutant(mutantDnaSix));
    }

    @Test
    public void shouldDetectMutantInDNASeven() {
        assertTrue(mutantBusiness.isMutant(mutantDnaSeven));
    }

    @Test
    public void shouldDetectMutantInDNAEight() {
        assertTrue(mutantBusiness.isMutant(mutantDnaEight));
    }

    @Test
    public void shouldDetectMutantInDNANine() {
        assertTrue(mutantBusiness.isMutant(mutantDnaNine));
    }

    @Test
    public void shouldDetectMutantInDNATen() {
        assertTrue(mutantBusiness.isMutant(mutantDnaTen));
    }

    @Test
    public void shouldDetectHumanInDNA() {
        assertFalse(mutantBusiness.isMutant(humanDna));
    }

}
