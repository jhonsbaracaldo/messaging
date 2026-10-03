package efinomina.message.efinomina.domain.model.entity;

import java.sql.Date;

public class PermissionEntity {

    private Integer id;
    private String code;
    private String name;
    private String description;
    private boolean active;
    private Date created_at;

    public PermissionEntity(Integer id, String code, String name, String description, boolean active, Date created_at) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.active = active;
        this.created_at = created_at;
    }

    public PermissionEntity() {
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
