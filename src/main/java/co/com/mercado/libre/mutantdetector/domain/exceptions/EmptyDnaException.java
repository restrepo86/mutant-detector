package co.com.mercado.libre.mutantdetector.domain.exceptions;

public class EmptyDnaException extends InvalidRequestException {
    public EmptyDnaException(String message) {
        super(message);
    }
}
