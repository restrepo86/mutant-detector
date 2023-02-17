package co.com.mercado.libre.mutant.detector.usecase.transversal.enums;

public enum UseCaseEnum {

    GENERIC("generic", 0),
    DETECT_MUTANT("detectMutant", 1),
    GET_STATS("getStats", 2);

    private final String useCaseName;
    private final int useCaseCode;

    UseCaseEnum(String useCaseName, int useCaseCode) {
        this.useCaseName = useCaseName;
        this.useCaseCode = useCaseCode;
    }

    public String getUseCaseName() {
        return useCaseName.concat("UseCase");
    }

    public int getUseCaseCode() {
        return useCaseCode;
    }
}
