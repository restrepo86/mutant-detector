package co.com.mercado.libre.mutant.detector.configuration;

import co.com.mercado.libre.mutant.detector.api.mutant.gateways.DetectMutantGateway;
import co.com.mercado.libre.mutant.detector.api.mutant.gateways.GetMutantStatsGateway;
import co.com.mercado.libre.mutant.detector.api.transversal.helpers.ServerRequestToUseCaseInputObjectHelper;
import co.com.mercado.libre.mutant.detector.api.transversal.helpers.ServerResponseHelper;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.DetectMutantUseCase;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.DnaInputDTO;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations.Validations;
import co.com.mercado.libre.mutant.detector.usecase.mutant.stats.dto.StatsDTO;
import co.com.mercado.libre.mutant.detector.usecase.mutant.stats.gateways.IMutantStatsUseCase;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IResponseHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class EntryPointsConfigurationTest {

    @InjectMocks
    private EntryPointsConfiguration entryPointsConfiguration;

    @Mock
    private DetectMutantUseCase detectMutantUseCase;

    @Mock
    private Validator validator;

    @Mock
    private ServerResponseHelper<Void> voidServerResponseHelper;

    @Mock
    private Validations validations;

    @Mock
    private ServerRequestToUseCaseInputObjectHelper<DnaInputDTO> useCaseInputObjectHelper;

    @Mock
    private IMutantStatsUseCase iUseCase;

    @Mock
    private IResponseHelper<StatsDTO, ServerResponse> responseHelper;

    @Mock
    private ServerRequestToUseCaseInputObjectHelper<Void> useCaseInput;

    @Test
    void shouldBuildWebFluxProperties() {
        WebFluxProperties webFluxProperties = entryPointsConfiguration.buildWebFluxProperties();
        assertNotNull(webFluxProperties);
    }

    @Test
    void shouldBuildDetectMutantGateway() {
        DetectMutantGateway detectMutantGateway = entryPointsConfiguration.buildDetectMutantGateway(
                validator, detectMutantUseCase, voidServerResponseHelper, useCaseInputObjectHelper, validations);
        assertNotNull(detectMutantGateway);
    }

    @Test
    void shouldBuildServerResponseHelperOfDeleteTask() {
        ServerResponseHelper<Void> serverResponseHelper
                = entryPointsConfiguration.buildServerResponseHelper();
        assertNotNull(serverResponseHelper);
    }

    @Test
    void shouldBuildServerRequestToUseCaseInputObjectHelper() {
        ServerRequestToUseCaseInputObjectHelper serverRequestToUseCaseInputObjectHelper =
                entryPointsConfiguration.buildServerRequestToUseCaseInputObjectHelper();
        assertNotNull(serverRequestToUseCaseInputObjectHelper);
    }

    @Test
    void shouldBuildGetMutantStatsGateway() {
        GetMutantStatsGateway getMutantStatsGateway = entryPointsConfiguration.buildGetMutantStatsGateway(iUseCase, responseHelper);
        assertNotNull(getMutantStatsGateway);
    }

}
