package co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations;

import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.exceptions.InvalidNitrogenBaseException;
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
class DnaHasInvalidNitrogenBaseTest {

    @InjectMocks
    private DnaHasInvalidNitrogenBase dnaHasInvalidNitrogenBase;

    private List<String> dna;
    private List<String> dnaInvalidNitrogenBaseOne;
    private List<String> dnaInvalidNitrogenBaseTwo;
    private List<String> dnaInvalidNitrogenBaseThree;

    @BeforeEach
    void setUp() {
        dna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        dnaInvalidNitrogenBaseOne = Arrays.asList("ATGJGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        dnaInvalidNitrogenBaseTwo = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGPAGG","CCCCTA","TCACTG");
        dnaInvalidNitrogenBaseThree = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGaAGG","CCCCTA","TCACTG");
    }

    @Test
    void shouldValidateDnaHasInvalidNitrogenBase() {
        assertDoesNotThrow(() ->
                dnaHasInvalidNitrogenBase.validate(dna));
    }

    @Test
    void shouldValidateDnaHasInvalidNitrogenBaseTestTwo() {
        InvalidNitrogenBaseException invalidNitrogenBaseException = assertThrows(InvalidNitrogenBaseException.class, () ->
                dnaHasInvalidNitrogenBase.validate(dnaInvalidNitrogenBaseOne));
        Assertions.assertEquals("The Nitrogen base it must be A, G, T, C, in capital letters", invalidNitrogenBaseException.getApplicationCodesEnum().getMessage());
    }

    @Test
    void shouldValidateDnaHasInvalidNitrogenBaseTestThree() {
        InvalidNitrogenBaseException invalidNitrogenBaseException = assertThrows(InvalidNitrogenBaseException.class, () ->
                dnaHasInvalidNitrogenBase.validate(dnaInvalidNitrogenBaseTwo));
        Assertions.assertEquals("The Nitrogen base it must be A, G, T, C, in capital letters", invalidNitrogenBaseException.getApplicationCodesEnum().getMessage());
    }

    @Test
    void shouldValidateDnaHasInvalidNitrogenBaseTestFour() {
        InvalidNitrogenBaseException invalidNitrogenBaseException = assertThrows(InvalidNitrogenBaseException.class, () ->
                dnaHasInvalidNitrogenBase.validate(dnaInvalidNitrogenBaseThree));
        Assertions.assertEquals("The Nitrogen base it must be A, G, T, C, in capital letters", invalidNitrogenBaseException.getApplicationCodesEnum().getMessage());
    }

}
