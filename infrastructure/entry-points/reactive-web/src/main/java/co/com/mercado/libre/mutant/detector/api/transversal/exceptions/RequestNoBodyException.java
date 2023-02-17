package co.com.mercado.libre.mutant.detector.api.transversal.exceptions;

import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;

public class RequestNoBodyException extends EntryPointException {
    public RequestNoBodyException(ApplicationCodesEnum applicationCodesEnum) {
        super(applicationCodesEnum);
    }
}
