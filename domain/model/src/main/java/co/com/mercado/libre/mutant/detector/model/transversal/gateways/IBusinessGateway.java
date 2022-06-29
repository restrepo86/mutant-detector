package co.com.mercado.libre.mutant.detector.model.transversal.gateways;

import reactor.core.publisher.Mono;

public interface IBusinessGateway<I, O> {
    Mono<O> executeBusinessRules(I inputObject);
}
