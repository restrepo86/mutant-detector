package co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.exceptions;

import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;
import co.com.mercado.libre.mutant.detector.usecase.transversal.exceptions.UseCaseException;

public class ReactiveRepositoryException extends UseCaseException {
    public ReactiveRepositoryException(ApplicationCodesEnum applicationCodesEnum) {
        super(applicationCodesEnum);
    }
}
