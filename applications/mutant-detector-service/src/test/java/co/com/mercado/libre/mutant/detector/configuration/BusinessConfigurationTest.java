package co.com.mercado.libre.mutant.detector.configuration;

import co.com.mercado.libre.mutant.detector.business.mutant.MutantBusiness;
import co.com.mercado.libre.mutant.detector.business.mutant.rules.IsMutant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BusinessConfigurationTest {

    @InjectMocks
    private BusinessConfiguration businessConfiguration;
    @Mock
    private IsMutant isMutant;

    @Test
    void shouldBuildPruebaBusinessGateway() {
        MutantBusiness mutantBusiness =
                businessConfiguration.buildMutantBusinessGateway(isMutant);
        assertNotNull(mutantBusiness);
    }

}
