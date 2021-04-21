package co.com.mercado.libre.mutantdetector.domain.validations;

import co.com.mercado.libre.mutantdetector.domain.exceptions.InvalidSizeDnaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DnaHasInvalidSizeTest {

    @InjectMocks
    private DnaHasInvalidSize dnaHasInvalidSize;

    private List<String> dna;
    private List<String> invalidRowsSizeDna;
    private List<String> invalidColumnsSizeDna;
    private List<String> invalidColumnsSizeDnaTwo;
    private List<String> dnaSmallerThanFour;
    private List<String> dnaValidTwo;

    @BeforeEach
    public void setUp() {
        invalidRowsSizeDna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA");
        dna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        dnaValidTwo = Arrays.asList("ATGC", "ATGC", "AGTC", "AGTC");
        invalidColumnsSizeDna = Arrays.asList("ATGCG","CAGTG","TTATG","AGAAG","CCCCT","TCACT");
        invalidColumnsSizeDnaTwo = Arrays.asList("ATGCGA","CAGTGC","TATGT","AGAAGG","CCCCTA","TCACTG");
        dnaSmallerThanFour = Arrays.asList("AGC", "TCG", "AGC");
    }

    @Test
    public void shouldValidateIfDnaHasInvalidSize() {
        assertDoesNotThrow(() ->
                dnaHasInvalidSize.validate(dna));
    }

    @Test
    public void shouldValidateIfDnaHasInvalidSizeTestValidTwo() {
        assertDoesNotThrow(() ->
                dnaHasInvalidSize.validate(dnaValidTwo));
    }

    @Test
    public void shouldValidateIfDnaHasInvalidSizeTestTwo() {
        InvalidSizeDnaException invalidSizeDnaException = assertThrows(InvalidSizeDnaException.class, () ->
                dnaHasInvalidSize.validate(invalidRowsSizeDna));
        assertEquals("The DNA size is invalid, because it must be N x N matrix and N > 3", invalidSizeDnaException.getMessage());
    }

    @Test
    public void shouldValidateIfDnaHasInvalidSizeTestThree() {
        InvalidSizeDnaException invalidSizeDnaException = assertThrows(InvalidSizeDnaException.class, () ->
                dnaHasInvalidSize.validate(invalidColumnsSizeDna));
        assertEquals("The DNA size is invalid, because it must be N x N matrix and N > 3", invalidSizeDnaException.getMessage());
    }

    @Test
    public void shouldValidateIfDnaHasInvalidSizeTestFour() {
        InvalidSizeDnaException invalidSizeDnaException = assertThrows(InvalidSizeDnaException.class, () ->
                dnaHasInvalidSize.validate(invalidColumnsSizeDnaTwo));
        assertEquals("The DNA size is invalid, because it must be N x N matrix and N > 3", invalidSizeDnaException.getMessage());
    }

    @Test
    public void shouldValidateIfDnaHasInvalidSizeTestFive() {
        InvalidSizeDnaException invalidSizeDnaException = assertThrows(InvalidSizeDnaException.class, () ->
                dnaHasInvalidSize.validate(dnaSmallerThanFour));
        assertEquals("The DNA size is invalid, because it must be N x N matrix and N > 3", invalidSizeDnaException.getMessage());
    }

}
