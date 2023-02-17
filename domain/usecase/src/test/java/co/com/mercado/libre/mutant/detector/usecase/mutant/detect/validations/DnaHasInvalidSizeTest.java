package co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations;

import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.exceptions.InvalidSizeDnaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class DnaHasInvalidSizeTest {

    @InjectMocks
    private DnaHasInvalidSize dnaHasInvalidSize;

    private List<String> dna;
    private List<String> invalidRowsSizeDna;
    private List<String> invalidColumnsSizeDna;
    private List<String> invalidColumnsSizeDnaTwo;
    private List<String> dnaSmallerThanFour;
    private List<String> dnaValidTwo;

    @BeforeEach
    void setUp() {
        invalidRowsSizeDna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA");
        dna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        dnaValidTwo = Arrays.asList("ATGC", "ATGC", "AGTC", "AGTC");
        invalidColumnsSizeDna = Arrays.asList("ATGCG","CAGTG","TTATG","AGAAG","CCCCT","TCACT");
        invalidColumnsSizeDnaTwo = Arrays.asList("ATGCGA","CAGTGC","TATGT","AGAAGG","CCCCTA","TCACTG");
        dnaSmallerThanFour = Arrays.asList("AGC", "TCG", "AGC");
    }

    @Test
    void shouldValidateIfDnaHasInvalidSize() {
        assertDoesNotThrow(() ->
                dnaHasInvalidSize.validate(dna));
    }

    @Test
    void shouldValidateIfDnaHasInvalidSizeTestValidTwo() {
        assertDoesNotThrow(() ->
                dnaHasInvalidSize.validate(dnaValidTwo));
    }

    @Test
    void shouldValidateIfDnaHasInvalidSizeTestTwo() {
        InvalidSizeDnaException invalidSizeDnaException = assertThrows(InvalidSizeDnaException.class, () ->
                dnaHasInvalidSize.validate(invalidRowsSizeDna));
        Assertions.assertEquals("The DNA size is invalid, because it must be N x N matrix and N > 3", invalidSizeDnaException.getApplicationCodesEnum().getMessage());
    }

    @Test
    void shouldValidateIfDnaHasInvalidSizeTestThree() {
        InvalidSizeDnaException invalidSizeDnaException = assertThrows(InvalidSizeDnaException.class, () ->
                dnaHasInvalidSize.validate(invalidColumnsSizeDna));
        Assertions.assertEquals("The DNA size is invalid, because it must be N x N matrix and N > 3", invalidSizeDnaException.getApplicationCodesEnum().getMessage());
    }

    @Test
    void shouldValidateIfDnaHasInvalidSizeTestFour() {
        InvalidSizeDnaException invalidSizeDnaException = assertThrows(InvalidSizeDnaException.class, () ->
                dnaHasInvalidSize.validate(invalidColumnsSizeDnaTwo));
        Assertions.assertEquals("The DNA size is invalid, because it must be N x N matrix and N > 3", invalidSizeDnaException.getApplicationCodesEnum().getMessage());
    }

    @Test
    void shouldValidateIfDnaHasInvalidSizeTestFive() {
        InvalidSizeDnaException invalidSizeDnaException = assertThrows(InvalidSizeDnaException.class, () ->
                dnaHasInvalidSize.validate(dnaSmallerThanFour));
        Assertions.assertEquals("The DNA size is invalid, because it must be N x N matrix and N > 3", invalidSizeDnaException.getApplicationCodesEnum().getMessage());
    }

}
