package efinomina.message.efinomina.aplication.dto;

import java.time.LocalDateTime;

public class AuditLogDTO {
    private Long id;
    private Long userId;
    private String module;
    private String action;
    private String tableName;
    private Long recordId;
    private String oldData;
    private String newData;
    private String ipAddress;
    private LocalDateTime createdAt;

    public AuditLogDTO() {}

    public AuditLogDTO(Long id, Long userId, String module, String action, String tableName, Long recordId, String oldData, String newData, String ipAddress, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.module = module;
        this.action = action;
        this.tableName = tableName;
        this.recordId = recordId;
        this.oldData = oldData;
        this.newData = newData;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }
    public Long getRecordId() { return recordId; }
    public void setRecordId(Long recordId) { this.recordId = recordId; }
    public String getOldData() { return oldData; }
    public void setOldData(String oldData) { this.oldData = oldData; }
    public String getNewData() { return newData; }
    public void setNewData(String newData) { this.newData = newData; }
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
