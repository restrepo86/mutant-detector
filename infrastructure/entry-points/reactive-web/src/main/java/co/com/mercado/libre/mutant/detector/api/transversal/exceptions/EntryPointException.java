package co.com.mercado.libre.mutant.detector.api.transversal.exceptions;

import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;
import co.com.mercado.libre.mutant.detector.usecase.transversal.exceptions.UseCaseException;

public class EntryPointException extends UseCaseException {

    public EntryPointException(ApplicationCodesEnum applicationCodesEnum) {
        super(applicationCodesEnum);
    }

    public EntryPointException(ApplicationCodesEnum applicationCodesEnum, String message) {
        super(applicationCodesEnum, message);
    }

}
