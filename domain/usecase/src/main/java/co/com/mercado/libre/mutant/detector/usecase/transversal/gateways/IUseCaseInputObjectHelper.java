package co.com.mercado.libre.mutant.detector.usecase.transversal.gateways;

import reactor.core.publisher.Mono;

public interface IUseCaseInputObjectHelper<I, O> {
    Mono<O> build(I inputObject);
}
