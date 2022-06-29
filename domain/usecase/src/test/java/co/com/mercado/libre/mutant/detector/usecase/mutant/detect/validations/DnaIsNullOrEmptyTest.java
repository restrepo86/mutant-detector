package co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations;

import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.exceptions.EmptyDnaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class DnaIsNullOrEmptyTest {

    @InjectMocks
    private DnaIsNullOrEmpty dnaIsNullOrEmpty;

    private List<String> dna;
    private List<String> emptyDna;

    @BeforeEach
    void setUp() {
        dna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        emptyDna = new ArrayList<>();
    }

    @Test
    void shouldValidateDnaIsNullOrEmpty() {
        assertDoesNotThrow(() ->
                dnaIsNullOrEmpty.validate(dna));
    }

    @Test
    void shouldValidateDnaIsNullOrEmptyTestTwo() {
        EmptyDnaException emptyDnaException = assertThrows(EmptyDnaException.class, () ->
                dnaIsNullOrEmpty.validate(emptyDna));
        Assertions.assertEquals("The DNA is empty", emptyDnaException.getApplicationCodesEnum().getMessage());
    }

    @Test
    void shouldValidateDnaIsNullOrEmptyTestThree() {
        EmptyDnaException emptyDnaException = assertThrows(EmptyDnaException.class, () ->
                dnaIsNullOrEmpty.validate(null));
        Assertions.assertEquals("The DNA is empty", emptyDnaException.getApplicationCodesEnum().getMessage());
    }

}
