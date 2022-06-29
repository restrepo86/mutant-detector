package co.com.mercado.libre.mutant.detector.api.mutant.handler;

import co.com.mercado.libre.mutant.detector.api.mutant.gateways.DetectMutantGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.web.reactive.function.server.ServerRequest;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DetectMutantHandlerTest {

    @InjectMocks
    private DetectMutantHandler detectMutantHandler;

    @Mock
    private DetectMutantGateway detectMutantGateway;

    private ServerRequest serverRequest;

    @BeforeEach
    void setUp() {
        serverRequest = MockServerRequest.builder().build();
        detectMutantHandler = new DetectMutantHandler(detectMutantGateway);
    }

    @Test
    void shouldGetCleanArchitectureHandlerExample() {
        detectMutantHandler.detectMutant(serverRequest);
        verify(detectMutantGateway).execute(serverRequest);
    }

}
