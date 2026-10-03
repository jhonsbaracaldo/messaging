package efinomina.message.efinomina.domain.exception;

public class NotFoundException extends RuntimeException {

    public static final String PERMISSION_NOT_FOUND = "PermissionController no encontrado";
    public static final String CATEGORIA_NOT_FOUND  = "Categoría no encontrada";
    public static final String CLIENTE_NOT_FOUND    = "Cliente no encontrado";
    public static final String SIN_DATOS = "lista sin datos";
    public NotFoundException(String message) {
        super(message);
    }
}
