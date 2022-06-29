package co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.exceptions;

import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;

public class TaskNotFoundException extends ReactiveRepositoryException {
    public TaskNotFoundException(ApplicationCodesEnum applicationCodesEnum) {
        super(applicationCodesEnum);
    }
}
