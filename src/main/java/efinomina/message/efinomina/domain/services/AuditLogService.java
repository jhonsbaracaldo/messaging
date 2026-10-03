package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.AuditLog;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryAuditLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogService {

    private final RepositoryAuditLog repository;

    public AuditLogService(RepositoryAuditLog repository) {
        this.repository = repository;
    }

    public AuditLog save(AuditLog auditLog) {
        auditLog.setCreatedAt(LocalDateTime.now());
        return repository.save(auditLog);
    }

    public List<AuditLog> findAll() {
        return repository.findAll();
    }

    public List<AuditLog> findByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<AuditLog> findByModule(String module) {
        return repository.findByModule(module);
    }

    public List<AuditLog> findByTable(String tableName) {
        return repository.findByTableName(tableName);
    }
}
