package co.com.mercado.libre.mutant.detector.usecase.transversal.enums;

public enum ApplicationCodesEnum {

    UNCHECKED_ERROR("0", UseCaseEnum.GENERIC, "Tuvimos un problema interno, por favor intenta mas tarde", 500),
    INVALID_REQUEST_OBJECT("1", UseCaseEnum.GENERIC, "Datos de entrada no v\u00e1lidos", 400),
    DEBES_ENVIAR_UN_CUERPO_DE_SOLICITUD_PARA_EL_CONSUMO_DE_ESTE_SERVICIO("2", UseCaseEnum.GENERIC, "Debes enviar un cuerpo de solicitud para el consumo de este servicio", 400),
    NO_SE_PUDO_CONSUMIR_SERVICIO_POR("3", UseCaseEnum.GENERIC, "No se pudo consumir servicio por -> ", 500),
    EMPTY_DNA_EXCEPTION("4", UseCaseEnum.DETECT_MUTANT, "The DNA is empty", 400),
    INVALID_NITROGEN_BASE_EXCEPTION("5", UseCaseEnum.DETECT_MUTANT, "The Nitrogen base it must be A, G, T, C, in capital letters", 400),
    INVALID_SIZE_DNA_EXCEPTION("6", UseCaseEnum.DETECT_MUTANT, "The DNA size is invalid, because it must be N x N matrix and N > 3", 400),
    DNA_MUTANT("7", UseCaseEnum.DETECT_MUTANT, "dna belongs to mutant!", 201),
    DNA_HUMAN("8", UseCaseEnum.DETECT_MUTANT, "dna belongs to human!", 201);


    public static final String SEPARATOR = "-";

    private final String errorCode;
    private final UseCaseEnum useCase;
    private final String message;
    private final int  statusCode;

    ApplicationCodesEnum(String errorCode, UseCaseEnum useCase, String message, int statusCode) {
        this.errorCode = errorCode;
        this.useCase = useCase;
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return new StringBuilder()
                .append(useCase.getUseCaseCode())
                .append(SEPARATOR)
                .append(errorCode)
                .append(SEPARATOR)
                .append(statusCode)
                .toString();
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
