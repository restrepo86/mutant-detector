package co.com.mercado.libre.mutant.detector.usecase.transversal.gateways;

import reactor.core.publisher.Mono;

public interface IResponseHelper<I, O> {
    Mono<O> build(Mono<I> useCaseOutputObject);
}
