package efinomina.message.efinomina.domain.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Client {
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String notes;
    private Boolean active;
    private LocalDateTime createdAt;
    private Long preferredBarberId;

    public Client() {}

    public Client(Long id, String name, String lastName, String phone, String email, String notes, Boolean active, LocalDateTime createdAt, Long preferredBarberId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.notes = notes;
        this.active = active;
        this.createdAt = createdAt;
        this.preferredBarberId = preferredBarberId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Long getPreferredBarberId() { return preferredBarberId; }
    public void setPreferredBarberId(Long preferredBarberId) { this.preferredBarberId = preferredBarberId; }
}
