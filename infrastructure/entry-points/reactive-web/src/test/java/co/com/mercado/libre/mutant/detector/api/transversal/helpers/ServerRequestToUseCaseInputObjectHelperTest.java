package co.com.mercado.libre.mutant.detector.api.transversal.helpers;

import co.com.mercado.libre.mutant.detector.api.transversal.exceptions.RequestException;
import co.com.mercado.libre.mutant.detector.api.transversal.exceptions.RequestNoBodyException;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.DnaInputDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class ServerRequestToUseCaseInputObjectHelperTest {

    @InjectMocks
    private ServerRequestToUseCaseInputObjectHelper<DnaInputDTO> serverRequestToUseCaseInputObjectHelper;

    private ServerRequest serverRequest;
    private ServerRequest serverRequestWithErrors;
    private DnaInputDTO taskInputDTO;
    private ServerRequest serverRequestWithoutNoBody;

    @BeforeEach
    void setUp() {
        serverRequestToUseCaseInputObjectHelper
                = new ServerRequestToUseCaseInputObjectHelper(DnaInputDTO.class);
        taskInputDTO = DnaInputDTO.builder()
                .dna(Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"))
                .build();
        serverRequest = MockServerRequest.builder()
                .body(Mono.just(taskInputDTO));
        serverRequestWithErrors = MockServerRequest.builder().build();
        serverRequestWithoutNoBody = MockServerRequest.builder().body(Mono.empty());
    }

    @Test
    void shouldBuildUseCaseInputObjectWithServerRequest() {
        StepVerifier
                .create(serverRequestToUseCaseInputObjectHelper.build(serverRequest))
                .expectNext(taskInputDTO)
                .verifyComplete();
    }

    @Test
    void shouldBuildUseCaseInputObjectWithErrors() {
        StepVerifier
                .create(serverRequestToUseCaseInputObjectHelper.build(serverRequestWithErrors))
                .expectError(RequestException.class)
                .verify();
    }

    @Test
    void shouldBuildUseCaseInputObjectWithErrorsRequestNoBody() {
        StepVerifier
                .create(serverRequestToUseCaseInputObjectHelper.build(serverRequestWithoutNoBody))
                .expectError(RequestNoBodyException.class)
                .verify();
    }

}
