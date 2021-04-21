package co.com.mercado.libre.mutantdetector.domain.exceptions;

public class InvalidRequestException extends Exception {
    public InvalidRequestException(String message) {
        super(message);
    }

}
