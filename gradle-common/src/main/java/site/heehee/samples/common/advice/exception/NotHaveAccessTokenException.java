package site.heehee.samples.common.advice.exception;

public class NotHaveAccessTokenException extends RuntimeException {

    public NotHaveAccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotHaveAccessTokenException(String message) {
        super(message);
    }

    public NotHaveAccessTokenException() {
    }
}
