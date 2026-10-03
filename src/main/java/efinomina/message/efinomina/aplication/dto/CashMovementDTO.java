package efinomina.message.efinomina.aplication.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CashMovementDTO {
    private Long id;
    private String type;
    private BigDecimal amount;
    private String description;
    private Long createdById;
    private LocalDateTime createdAt;

    public CashMovementDTO() {}

    public CashMovementDTO(Long id, String type, BigDecimal amount, String description, Long createdById, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.createdById = createdById;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getCreatedById() { return createdById; }
    public void setCreatedById(Long createdById) { this.createdById = createdById; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
