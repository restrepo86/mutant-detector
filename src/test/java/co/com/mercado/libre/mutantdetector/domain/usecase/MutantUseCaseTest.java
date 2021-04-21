package co.com.mercado.libre.mutantdetector.domain.usecase;

import co.com.mercado.libre.mutantdetector.domain.business.MutantBusiness;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class MutantUseCaseTest {
    
    @InjectMocks
    private MutantUseCase mutantUseCase;

    @Mock
    private MutantBusiness mutantBusiness;

    private List<String> dna;

    @Test
    public void shouldValidateIsMutant() {
        mutantUseCase.validateDna(dna);
        verify(mutantBusiness).isMutant(dna);
    }
    
}
