package co.com.mercado.libre.mutant.detector.configuration;

import co.com.mercado.libre.mutant.detector.model.mutant.gateways.IMutantBusinessGateway;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.DetectMutantUseCase;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations.DnaHasInvalidNitrogenBase;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations.DnaHasInvalidSize;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations.DnaIsNullOrEmpty;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations.Validations;
import co.com.mercado.libre.mutant.detector.usecase.mutant.gateways.persistence.IMutantDetectorHistoryServices;
import co.com.mercado.libre.mutant.detector.usecase.mutant.stats.GetStatsUseCase;
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

    @Mock
    private DnaIsNullOrEmpty dnaIsNullOrEmpty;

    @Mock
    private DnaHasInvalidNitrogenBase dnaHasInvalidNitrogenBase;

    @Mock
    private DnaHasInvalidSize dnaHasInvalidSize;

    @Mock
    private IMutantDetectorHistoryServices mutantDetectorHistoryServices;

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

    @Test
    void shouldBuildValidations() {
        Validations validations = useCaseConfiguration.buildValidations(dnaIsNullOrEmpty, dnaHasInvalidSize, dnaHasInvalidNitrogenBase);
        assertNotNull(validations);
    }

    @Test
    void shouldBuildDnaIsNullOrEmpty() {
        DnaIsNullOrEmpty dnaIsNullOrEmpty = useCaseConfiguration.buildDnaIsNullOrEmpty();
        assertNotNull(dnaIsNullOrEmpty);
    }

    @Test
    void shouldBuildDnaHasInvalidSize() {
        DnaHasInvalidSize dnaHasInvalidSize = useCaseConfiguration.buildDnaHasInvalidSize();
        assertNotNull(dnaHasInvalidSize);
    }

    @Test
    void shouldBuildDnaHasInvalidNitrogenBase() {
        DnaHasInvalidNitrogenBase dnaHasInvalidNitrogenBase = useCaseConfiguration.buildDnaHasInvalidNitrogenBase();
        assertNotNull(dnaHasInvalidNitrogenBase);
    }

    @Test
    void shouldBuildMutantStatsUseCase() {
        GetStatsUseCase getStatsUseCase = useCaseConfiguration.buildMutantStatsUseCase(mutantDetectorHistoryServices);
        assertNotNull(getStatsUseCase);
    }

}
