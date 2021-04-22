package co.com.mercado.libre.mutantdetector.domain.validations;

import co.com.mercado.libre.mutantdetector.domain.exceptions.EmptyDnaException;
import co.com.mercado.libre.mutantdetector.domain.exceptions.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest
@ContextConfiguration(classes = { DnaIsNullOrEmpty.class })
public class DnaIsNullOrEmptyTest {

    @InjectMocks
    private DnaIsNullOrEmpty dnaIsNullOrEmpty;

    private List<String> dna;
    private List<String> emptyDna;

    @BeforeEach
    public void setUp() {
        dna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        emptyDna = new ArrayList<>();
    }

    @Test
    public void shouldValidateDnaIsNullOrEmpty() {
        assertDoesNotThrow(() ->
                dnaIsNullOrEmpty.validate(dna));
    }

    @Test
    public void shouldValidateDnaIsNullOrEmptyTestTwo() {
        InvalidRequestException invalidRequestException = assertThrows(EmptyDnaException.class, () ->
                dnaIsNullOrEmpty.validate(emptyDna));
        assertEquals("The DNA is empty", invalidRequestException.getMessage());
    }

    @Test
    public void shouldValidateDnaIsNullOrEmptyTestThree() {
        InvalidRequestException invalidRequestException = assertThrows(EmptyDnaException.class, () ->
                dnaIsNullOrEmpty.validate(null));
        assertEquals("The DNA is empty", invalidRequestException.getMessage());
    }

}
