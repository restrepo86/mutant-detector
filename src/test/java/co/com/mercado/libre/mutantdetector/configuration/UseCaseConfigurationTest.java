package co.com.mercado.libre.mutantdetector.configuration;

import co.com.mercado.libre.mutantdetector.domain.business.MutantBusiness;
import co.com.mercado.libre.mutantdetector.domain.validations.Validations;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@WebMvcTest
@ContextConfiguration(classes = { UseCaseConfiguration.class })
public class UseCaseConfigurationTest {

    @InjectMocks
    private UseCaseConfiguration useCaseConfiguration;

    @MockBean
    private MutantBusiness mutantBusiness;

    @MockBean
    private Validations validations;

    @Test
    public void shouldBuildMutantUseCase() {
        useCaseConfiguration.buildMutantUseCase(mutantBusiness, validations);
    }

}
