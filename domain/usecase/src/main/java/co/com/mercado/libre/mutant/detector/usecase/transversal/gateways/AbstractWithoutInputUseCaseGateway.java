package co.com.mercado.libre.mutant.detector.usecase.transversal.gateways;

import reactor.core.publisher.Mono;

public abstract class AbstractWithoutInputUseCaseGateway<O, A> {

    private final IWithoutInputUseCase<O> iUseCase;
    private final IResponseHelper<O, A> responseHelper;

    protected AbstractWithoutInputUseCaseGateway(
            IWithoutInputUseCase<O> iUseCase,
            IResponseHelper<O, A> responseHelper) {
        this.iUseCase = iUseCase;
        this.responseHelper = responseHelper;
    }

    public Mono<A> execute() {
        return this.executeUseCase()
                .flatMap(this::buildOutputObject);
    }

    protected Mono<O> executeUseCase() {
        return iUseCase.execute();
    }

    protected Mono<A> buildOutputObject(O useCaseOutputObject) {
        return responseHelper.build(Mono.justOrEmpty(useCaseOutputObject));
    }

}
