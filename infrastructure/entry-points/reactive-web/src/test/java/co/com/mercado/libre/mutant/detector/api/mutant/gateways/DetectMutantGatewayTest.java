package co.com.mercado.libre.mutant.detector.api.mutant.gateways;

import co.com.mercado.libre.mutant.detector.api.transversal.helpers.ServerRequestToUseCaseInputObjectHelper;
import co.com.mercado.libre.mutant.detector.api.transversal.helpers.ServerResponseHelper;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.DetectMutantUseCase;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.DnaInputDTO;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations.Validations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DetectMutantGatewayTest {

    @InjectMocks
    private DetectMutantGateway detectMutantGateway;

    @Mock
    private DetectMutantUseCase detectMutantUseCase;

    @Mock
    private ServerResponseHelper<Void> serverResponseHelper;

    @Mock
    private Validations validations;

    @Mock
    private Validator validator;

    @Mock
    private ServerRequestToUseCaseInputObjectHelper<DnaInputDTO> useCaseInputObjectHelper;

    private DnaInputDTO dnaInputDTO;
    private ServerRequest serverRequest;
    private Mono<ServerResponse> serverResponse;

    @BeforeEach
    void setUp() {
        detectMutantGateway = new DetectMutantGateway(
                DnaInputDTO.class,
                validator,
                detectMutantUseCase,
                serverResponseHelper,
                useCaseInputObjectHelper,
                validations
        );
        dnaInputDTO = DnaInputDTO.builder()
                .dna(Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"))
                .build();
        serverRequest = MockServerRequest.builder()
                .body(Mono.just(dnaInputDTO));
        serverResponse = ServerResponse.status(201).build();
    }

    @Test
    void shouldExecuteDeleteTaskUseCase() {
        when(useCaseInputObjectHelper.build(serverRequest)).thenReturn(Mono.just(dnaInputDTO));
        when(detectMutantUseCase.execute(any())).thenReturn(Mono.empty());
        when(serverResponseHelper.build(any())).thenReturn(serverResponse);
        StepVerifier
                .create(detectMutantGateway.execute(serverRequest))
                .expectNext(serverResponse.block())
                .verifyComplete();
        verify(useCaseInputObjectHelper).build(serverRequest);
        verify(detectMutantUseCase).execute(any());
        verify(serverResponseHelper).build(any());
    }

}
