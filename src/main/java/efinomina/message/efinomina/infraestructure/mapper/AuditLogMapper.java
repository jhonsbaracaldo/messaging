package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.AuditLogDTO;
import efinomina.message.efinomina.domain.model.entity.AuditLog;

public interface AuditLogMapper {

    static AuditLog toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.AuditLog e) {
        if (e == null) return null;
        Long userId = e.getUser() != null ? e.getUser().getId() : null;
        return new AuditLog(e.getId(), userId, e.getModule(), e.getAction(), e.getTableName(), e.getRecordId(), e.getOldData(), e.getNewData(), e.getIpAddress(), e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.AuditLog toEntity(AuditLog d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.AuditLog e = new efinomina.message.efinomina.infraestructure.persistence.entity.AuditLog();
        e.setId(d.getId());
        if (d.getUserId() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.User user = new efinomina.message.efinomina.infraestructure.persistence.entity.User();
            user.setId(d.getUserId());
            e.setUser(user);
        }
        e.setModule(d.getModule());
        e.setAction(d.getAction());
        e.setTableName(d.getTableName());
        e.setRecordId(d.getRecordId());
        e.setOldData(d.getOldData());
        e.setNewData(d.getNewData());
        e.setIpAddress(d.getIpAddress());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    static AuditLogDTO toDTO(AuditLog d) {
        if (d == null) return null;
        return new AuditLogDTO(d.getId(), d.getUserId(), d.getModule(), d.getAction(), d.getTableName(), d.getRecordId(), d.getOldData(), d.getNewData(), d.getIpAddress(), d.getCreatedAt());
    }

    static AuditLog toDomainFromDTO(AuditLogDTO dto) {
        if (dto == null) return null;
        return new AuditLog(dto.getId(), dto.getUserId(), dto.getModule(), dto.getAction(), dto.getTableName(), dto.getRecordId(), dto.getOldData(), dto.getNewData(), dto.getIpAddress(), dto.getCreatedAt());
    }
}
