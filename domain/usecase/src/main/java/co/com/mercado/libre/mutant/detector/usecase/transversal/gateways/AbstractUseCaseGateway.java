package co.com.mercado.libre.mutant.detector.usecase.transversal.gateways;

import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;
import co.com.mercado.libre.mutant.detector.usecase.transversal.exceptions.UseCaseException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractUseCaseGateway<I, O , R, A> {

    private final Class<O> validationClass;
    private final Validator validator;
    private final IUseCase<O, R> iUseCase;
    private final IResponseHelper<R, A> responseHelper;
    private final IUseCaseInputObjectHelper<I, O> useCaseInputObjectHelper;

    protected AbstractUseCaseGateway(
            Class<O> validationClass,
            Validator validator,
            IUseCase<O, R> iUseCase,
            IResponseHelper<R, A> responseHelper, IUseCaseInputObjectHelper<I, O> useCaseInputObjectHelper) {
        this.validationClass = validationClass;
        this.validator = validator;
        this.iUseCase = iUseCase;
        this.responseHelper = responseHelper;
        this.useCaseInputObjectHelper = useCaseInputObjectHelper;
    }

    public Mono<A> execute(I inputObject) {
        return Mono.justOrEmpty(inputObject)
                .map(this::buildUseCaseInputObject)
                .map(this::validateInputObject)
                .map(this::executeUseCase)
                .flatMap(this::buildOutputObject);
    }

    protected Mono<O> buildUseCaseInputObject(final I inputObject) {
        return useCaseInputObjectHelper.build(inputObject);
    }

    protected Mono<O> validateInputObject(Mono<O> useCaseInputObject) {
        return useCaseInputObject
                .map(this::throwExceptionIfHasErrors);
    }

    private O throwExceptionIfHasErrors(O inputObject) {
        var errors = new BeanPropertyBindingResult(inputObject, this.validationClass.getName());
        this.validator.validate(inputObject, errors);
        Optional.ofNullable(errors)
                .map(Errors::getAllErrors)
                .filter(allErrors -> !allErrors.isEmpty())
                .ifPresent(errorList -> {
                    throw new UseCaseException(ApplicationCodesEnum.INVALID_REQUEST_OBJECT, errorList.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")));
                });
        return inputObject;
    }

    protected Mono<R> executeUseCase(Mono<O> useCaseInputObject) {
        return iUseCase.execute(useCaseInputObject);
    }

    protected Mono<A> buildOutputObject(Mono<R> useCaseOutputObject) {
        return responseHelper.build(useCaseOutputObject);
    }

}
