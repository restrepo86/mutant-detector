package co.com.mercado.libre.mutant.detector.usecase.mutant.detect.exceptions;

import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;
import co.com.mercado.libre.mutant.detector.usecase.transversal.exceptions.UseCaseException;

public class EmptyDnaException extends UseCaseException {

    public EmptyDnaException(ApplicationCodesEnum applicationCodesEnum) {
        super(applicationCodesEnum);
    }

}
