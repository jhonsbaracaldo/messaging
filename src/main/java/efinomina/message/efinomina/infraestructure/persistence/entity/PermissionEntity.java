package efinomina.message.efinomina.infraestructure.persistence.entity;

import jakarta.persistence.*;

import java.sql.Date;
@Table(name = "permisos")
@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "codigo", length = 50, unique = true)
    private String code;
    @Column(name = "nombre", length = 100)
    private String name;
    @Column(name = "descripcion", length = 255)
    private String description;
    @Column (name = "activo", nullable = true)
    private boolean active;
    @Column (name = "fecha_creacion", nullable = true)
    private Date created_at;

    public Permission(Integer id, String code, String name, String description, boolean active, Date created_at) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.active = active;
        this.created_at = created_at;
    }

    public Permission() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
