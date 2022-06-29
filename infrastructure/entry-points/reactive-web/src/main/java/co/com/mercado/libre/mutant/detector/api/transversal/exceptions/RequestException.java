package co.com.mercado.libre.mutant.detector.api.transversal.exceptions;

import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;

public class RequestException extends EntryPointException {
    public RequestException(ApplicationCodesEnum applicationCodesEnum) {
        super(applicationCodesEnum);
    }
}
