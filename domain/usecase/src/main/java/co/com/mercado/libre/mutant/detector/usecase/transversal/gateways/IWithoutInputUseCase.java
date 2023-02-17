package co.com.mercado.libre.mutant.detector.usecase.transversal.gateways;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface IWithoutInputUseCase<O> {
    Mono<O> execute();
}
