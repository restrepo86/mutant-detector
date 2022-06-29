package co.com.mercado.libre.mutant.detector.configuration;

import co.com.mercado.libre.mutant.detector.usecase.mutant.gateways.persistence.IMutantDetectorHistoryServices;
import co.com.mercado.libre.mutant.detector.model.mutant.gateways.IMutantBusinessGateway;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.DetectMutantUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UseCaseConfigurationTest {

    @InjectMocks
    private UseCaseConfiguration useCaseConfiguration;

    @Mock
    private IMutantBusinessGateway iMutantBusinessGateway;

    @Mock
    private IMutantDetectorHistoryServices iMutantDetectorHistoryServices;

    @Test
    void shouldBuildValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean
                = useCaseConfiguration.buildValidator();
        assertNotNull(localValidatorFactoryBean);
    }

    @Test
    void shouldBuildDetectMutantUseCase() {
        DetectMutantUseCase detectMutantUseCase =
                useCaseConfiguration.buildDetectMutantUseCase(iMutantBusinessGateway, iMutantDetectorHistoryServices);
        assertNotNull(detectMutantUseCase);
    }

}
