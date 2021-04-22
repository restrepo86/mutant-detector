package co.com.mercado.libre.mutantdetector.domain.validations;

import co.com.mercado.libre.mutantdetector.domain.exceptions.InvalidNitrogenBaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest
@ContextConfiguration(classes = { DnaHasInvalidNitrogenBase.class })
public class DnaHasInvalidNitrogenBaseTest {

    @InjectMocks
    private DnaHasInvalidNitrogenBase dnaHasInvalidNitrogenBase;

    private List<String> dna;
    private List<String> dnaInvalidNitrogenBaseOne;
    private List<String> dnaInvalidNitrogenBaseTwo;
    private List<String> dnaInvalidNitrogenBaseThree;

    @BeforeEach
    public void setUp() {
        dna = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        dnaInvalidNitrogenBaseOne = Arrays.asList("ATGJGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        dnaInvalidNitrogenBaseTwo = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGPAGG","CCCCTA","TCACTG");
        dnaInvalidNitrogenBaseThree = Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGaAGG","CCCCTA","TCACTG");
    }

    @Test
    public void shouldValidateDnaHasInvalidNitrogenBase() {
        assertDoesNotThrow(() ->
                dnaHasInvalidNitrogenBase.validate(dna));
    }

    @Test
    public void shouldValidateDnaHasInvalidNitrogenBaseTestTwo() {
        InvalidNitrogenBaseException invalidNitrogenBaseException = assertThrows(InvalidNitrogenBaseException.class, () ->
                dnaHasInvalidNitrogenBase.validate(dnaInvalidNitrogenBaseOne));
        assertEquals("The Nitrogen base it must be A, G, T, C, in capital letters", invalidNitrogenBaseException.getMessage());
    }

    @Test
    public void shouldValidateDnaHasInvalidNitrogenBaseTestThree() {
        InvalidNitrogenBaseException invalidNitrogenBaseException = assertThrows(InvalidNitrogenBaseException.class, () ->
                dnaHasInvalidNitrogenBase.validate(dnaInvalidNitrogenBaseTwo));
        assertEquals("The Nitrogen base it must be A, G, T, C, in capital letters", invalidNitrogenBaseException.getMessage());
    }

    @Test
    public void shouldValidateDnaHasInvalidNitrogenBaseTestFour() {
        InvalidNitrogenBaseException invalidNitrogenBaseException = assertThrows(InvalidNitrogenBaseException.class, () ->
                dnaHasInvalidNitrogenBase.validate(dnaInvalidNitrogenBaseThree));
        assertEquals("The Nitrogen base it must be A, G, T, C, in capital letters", invalidNitrogenBaseException.getMessage());
    }

}
