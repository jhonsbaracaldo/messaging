package efinomina.message.efinomina.domain.exception;

public class BadRequestException extends RuntimeException {


    public static final String CODIGO_REQUERIDO      = "El código es obligatorio";
    public static final String NOMBRE_REQUERIDO      = "El nombre es obligatorio";
    public static final String DATOS_INVALIDOS       = "Los datos enviados son inválidos";


    public BadRequestException(String message) {
        super(message);
    }
}
