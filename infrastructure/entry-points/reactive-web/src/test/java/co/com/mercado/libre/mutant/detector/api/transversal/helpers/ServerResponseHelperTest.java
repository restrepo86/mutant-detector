package co.com.mercado.libre.mutant.detector.api.transversal.helpers;

import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;
import co.com.mercado.libre.mutant.detector.usecase.transversal.exceptions.UseCaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ServerResponseHelperTest {

    private static final int BAD_REQUEST = 400;
    private static final int INTERNAL_SERVER_ERROR = 500;

    @InjectMocks
    private ServerResponseHelper<Void> serverResponseHelper;

    private ServerResponse serverResponse;
    private Mono<Void> pruebaOutputDTOUseCaseException;
    private Mono<Void> pruebaOutputDTOUseCaseExceptionUnchecked;
    private Mono<Void> pruebaOutputDTOUnrecognizedException;
    private Mono<Void> pruebaOutputDTOUseCaseExceptionWithMessage;

    @BeforeEach
    void setUp() {
        serverResponse = ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Void.class)
                .block();
        pruebaOutputDTOUseCaseException = Mono.error(new UseCaseException(ApplicationCodesEnum.INVALID_NITROGEN_BASE_EXCEPTION));
        pruebaOutputDTOUseCaseExceptionUnchecked = Mono.error(new UseCaseException(ApplicationCodesEnum.UNCHECKED_ERROR));
        pruebaOutputDTOUnrecognizedException = Mono.error(new Throwable("ERROR"));
        pruebaOutputDTOUseCaseExceptionWithMessage = Mono.error(new UseCaseException(ApplicationCodesEnum.INVALID_REQUEST_OBJECT, "ERROR"));
    }

    @Test
    void shouldBuildServerResponseErrorWithUseCaseException() {
        StepVerifier
                .create(serverResponseHelper.build(pruebaOutputDTOUseCaseException))
                .expectNextMatches(response -> BAD_REQUEST == response.rawStatusCode())
                .verifyComplete();
    }

    @Test
    void shouldBuildServerResponseErrorWithUseCaseExceptionTwo() {
        StepVerifier
                .create(serverResponseHelper.build(pruebaOutputDTOUseCaseExceptionUnchecked))
                .expectNextMatches(response -> INTERNAL_SERVER_ERROR == response.rawStatusCode())
                .verifyComplete();
    }

    @Test
    void shouldBuildServerResponseErrorWithUnrecognizedException() {
        StepVerifier
                .create(serverResponseHelper.build(pruebaOutputDTOUnrecognizedException))
                .expectNextMatches(response -> INTERNAL_SERVER_ERROR == response.rawStatusCode())
                .verifyComplete();
    }

    @Test
    void shouldBuildServerResponseErrorWithMessageParameterIntoUseCaseExceptionAnd() {
        StepVerifier
                .create(serverResponseHelper.build(pruebaOutputDTOUseCaseExceptionWithMessage))
                .expectNextMatches(response -> BAD_REQUEST == response.rawStatusCode())
                .verifyComplete();
    }

}
