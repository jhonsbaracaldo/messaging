package efinomina.message.efinomina.aplication.dto;

public class RegisterClientDTO {
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private Integer telefono;

    public RegisterClientDTO() {}

    public RegisterClientDTO(Integer idCliente, String nombre, String apellido, String correo, Integer telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public Integer getTelefono() { return telefono; }
    public void setTelefono(Integer telefono) { this.telefono = telefono; }
}

