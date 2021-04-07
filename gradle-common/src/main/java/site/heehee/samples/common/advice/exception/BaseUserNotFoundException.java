package site.heehee.samples.common.advice.exception;

public class BaseUserNotFoundException  extends RuntimeException {

    public BaseUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseUserNotFoundException(String message) {
        super(message);
    }

    public BaseUserNotFoundException() {
    }
}
