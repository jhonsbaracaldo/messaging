package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.AuditLogDTO;
import efinomina.message.efinomina.domain.model.entity.AuditLog;
import efinomina.message.efinomina.infraestructure.mapper.AuditLogMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryAuditLog;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuditLogUseCase {

    private final RepositoryAuditLog repositoryAuditLog;

    public AuditLogUseCase(RepositoryAuditLog repositoryAuditLog) {
        this.repositoryAuditLog = repositoryAuditLog;
    }

    public AuditLogDTO registrarAuditoria(AuditLogDTO dto) {
        AuditLog al = new AuditLog();
        al.setUserId(dto.getUserId());
        al.setModule(dto.getModule());
        al.setAction(dto.getAction());
        al.setTableName(dto.getTableName());
        al.setRecordId(dto.getRecordId());
        al.setOldData(dto.getOldData());
        al.setNewData(dto.getNewData());
        al.setIpAddress(dto.getIpAddress());
        al.setCreatedAt(LocalDateTime.now());
        return AuditLogMapper.toDTO(AuditLogMapper.toDomain(repositoryAuditLog.save(AuditLogMapper.toEntity(al))));
    }

    public Optional<AuditLogDTO> obtenerAuditoriaPorId(Long id) {
        return repositoryAuditLog.findById(id).map(e -> AuditLogMapper.toDTO(AuditLogMapper.toDomain(e)));
    }

    public List<AuditLogDTO> obtenerTodasLasAuditorias() {
        return repositoryAuditLog.findAll().stream()
                .map(e -> AuditLogMapper.toDTO(AuditLogMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<AuditLogDTO> obtenerAuditoriasPorUsuario(Long userId) {
        return repositoryAuditLog.findByUserId(userId).stream()
                .map(e -> AuditLogMapper.toDTO(AuditLogMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<AuditLogDTO> obtenerAuditoriasPorTabla(String tableName) {
        return repositoryAuditLog.findByTableName(tableName).stream()
                .map(e -> AuditLogMapper.toDTO(AuditLogMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<AuditLogDTO> obtenerAuditoriasPorModulo(String module) {
        return repositoryAuditLog.findByModule(module).stream()
                .map(e -> AuditLogMapper.toDTO(AuditLogMapper.toDomain(e)))
                .collect(Collectors.toList());
    }
}
