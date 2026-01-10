package test.com.stockify.shared.application.internal.exceptions;

public class ResourceAlreadyException extends RuntimeException {
    public ResourceAlreadyException(String message) {
        super(message);
    }
}
