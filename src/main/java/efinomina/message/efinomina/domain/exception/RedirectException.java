package efinomina.message.efinomina.domain.exception;

public class RedirectException extends RuntimeException {

    public static final String REQUEST_FILED = "no son los datos que se esperan";
    public RedirectException(String message) {
        super(message);
    }
}
