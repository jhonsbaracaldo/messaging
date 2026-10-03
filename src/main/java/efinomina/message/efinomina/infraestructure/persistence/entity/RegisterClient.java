package efinomina.message.efinomina.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "register_client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "nombre", length = 250)
    private String nombre;

    @Column(name = "apellido", length = 250)
    private String apellido;

    @Column(name = "correo", length = 250)
    private String correo;

    @Column(name = "telefono")
    private Integer telefono;

}

