package efinomina.message.efinomina.domain.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Purchase {
    private Long id;
    private Long supplierId;
    private BigDecimal total;
    private String invoiceNumber;
    private Long createdById;
    private LocalDateTime createdAt;

    public Purchase() {}

    public Purchase(Long id, Long supplierId, BigDecimal total, String invoiceNumber, Long createdById, LocalDateTime createdAt) {
        this.id = id;
        this.supplierId = supplierId;
        this.total = total;
        this.invoiceNumber = invoiceNumber;
        this.createdById = createdById;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public Long getCreatedById() { return createdById; }
    public void setCreatedById(Long createdById) { this.createdById = createdById; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
