package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.CashMovementDTO;
import efinomina.message.efinomina.domain.model.entity.CashMovement;

public interface CashMovementMapper {

    static CashMovement toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.CashMovement e) {
        if (e == null) return null;
        Long createdById = e.getCreatedBy() != null ? e.getCreatedBy().getId() : null;
        return new CashMovement(e.getId(), e.getType(), e.getAmount(), e.getDescription(), createdById, e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.CashMovement toEntity(CashMovement d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.CashMovement e = new efinomina.message.efinomina.infraestructure.persistence.entity.CashMovement();
        e.setId(d.getId());
        e.setType(d.getType());
        e.setAmount(d.getAmount());
        e.setDescription(d.getDescription());
        if (d.getCreatedById() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.User user = new efinomina.message.efinomina.infraestructure.persistence.entity.User();
            user.setId(d.getCreatedById());
            e.setCreatedBy(user);
        }
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    static CashMovementDTO toDTO(CashMovement d) {
        if (d == null) return null;
        return new CashMovementDTO(d.getId(), d.getType(), d.getAmount(), d.getDescription(), d.getCreatedById(), d.getCreatedAt());
    }

    static CashMovement toDomainFromDTO(CashMovementDTO dto) {
        if (dto == null) return null;
        return new CashMovement(dto.getId(), dto.getType(), dto.getAmount(), dto.getDescription(), dto.getCreatedById(), dto.getCreatedAt());
    }
}
