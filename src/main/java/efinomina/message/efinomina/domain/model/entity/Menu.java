package efinomina.message.efinomina.domain.model.entity;

import java.time.LocalDateTime;

public class Menu {
    private Long id;
    private String name;
    private String icon;
    private String path;
    private Integer orderNumber;
    private Boolean active;
    private LocalDateTime createdAt;

    public Menu() {}

    public Menu(Long id, String name, String icon, String path, Integer orderNumber, Boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.path = path;
        this.orderNumber = orderNumber;
        this.active = active;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
