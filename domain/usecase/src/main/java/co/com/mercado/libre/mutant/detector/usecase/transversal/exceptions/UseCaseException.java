package co.com.mercado.libre.mutant.detector.usecase.transversal.exceptions;

import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;
import lombok.Getter;

@Getter
public class UseCaseException extends RuntimeException {

    private final ApplicationCodesEnum applicationCodesEnum;

    public UseCaseException(ApplicationCodesEnum applicationCodesEnum) {
        super();
        this.applicationCodesEnum = applicationCodesEnum;
    }

    public UseCaseException(ApplicationCodesEnum applicationCodesEnum, String message) {
        super(message);
        this.applicationCodesEnum = applicationCodesEnum;
    }
}
