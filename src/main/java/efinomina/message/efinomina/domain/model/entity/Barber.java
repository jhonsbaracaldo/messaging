package efinomina.message.efinomina.domain.model.entity;

import java.time.LocalDateTime;

public class Barber {
    private Long id;
    private Long userId;
    private String name;
    private String specialty;
    private Integer experienceYears;
    private String description;
    private String photoUrl;


    private String phone;
    private Boolean active;
    private LocalDateTime createdAt;

    public Barber() {}

    public Barber(Long id, Long userId, String name, String phone, String specialty, Integer experienceYears, String description, String photoUrl, Boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.specialty = specialty;
        this.experienceYears = experienceYears;
        this.description = description;
        this.photoUrl = photoUrl;
        this.active = active;
        this.phone =phone;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
