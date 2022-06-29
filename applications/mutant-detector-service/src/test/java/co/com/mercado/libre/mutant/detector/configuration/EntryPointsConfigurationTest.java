package co.com.mercado.libre.mutant.detector.configuration;

import co.com.mercado.libre.mutant.detector.api.mutant.gateways.DetectMutantGateway;
import co.com.mercado.libre.mutant.detector.api.transversal.helpers.ServerResponseHelper;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations.Validations;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.DetectMutantUseCase;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.DnaInputDTO;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IUseCaseInputObjectHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;

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
    private IUseCaseInputObjectHelper<ServerRequest, DnaInputDTO> useCaseInputObjectHelper;

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

}
