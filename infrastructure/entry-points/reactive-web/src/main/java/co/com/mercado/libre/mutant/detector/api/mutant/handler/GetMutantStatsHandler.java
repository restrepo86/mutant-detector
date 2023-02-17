package co.com.mercado.libre.mutant.detector.api.mutant.handler;

import co.com.mercado.libre.mutant.detector.api.mutant.gateways.GetMutantStatsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetMutantStatsHandler {

    private final GetMutantStatsGateway getMutantStatsGateway;

    public Mono<ServerResponse> getStats() {
        return getMutantStatsGateway.execute();
    }

}
