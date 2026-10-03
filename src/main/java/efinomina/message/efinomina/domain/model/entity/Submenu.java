package efinomina.message.efinomina.domain.model.entity;

import java.time.LocalDateTime;

public class Submenu {
    private Long id;
    private Long menuId;
    private String name;
    private String icon;
    private String path;
    private Integer orderNumber;
    private Boolean active;
    private LocalDateTime createdAt;

    public Submenu() {}

    public Submenu(Long id, Long menuId, String name, String icon, String path, Integer orderNumber, Boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.menuId = menuId;
        this.name = name;
        this.icon = icon;
        this.path = path;
        this.orderNumber = orderNumber;
        this.active = active;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getMenuId() { return menuId; }
    public void setMenuId(Long menuId) { this.menuId = menuId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public Integer getOrderNumber() { return orderNumber; }
    public void setOrderNumber(Integer orderNumber) { this.orderNumber = orderNumber; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
