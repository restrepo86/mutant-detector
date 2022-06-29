package co.com.mercado.libre.mutant.detector.api.mutant.handler;

import co.com.mercado.libre.mutant.detector.api.mutant.gateways.DetectMutantGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class DetectMutantHandler {

    private final DetectMutantGateway detectMutantGateway;

    public Mono<ServerResponse> detectMutant(ServerRequest serverRequest) {
        return detectMutantGateway.execute(serverRequest);
    }

}
