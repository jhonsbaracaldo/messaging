package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.RoleDTO;
import efinomina.message.efinomina.domain.model.entity.Role;

public interface RoleMapper {

    static Role toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Role e) {
        if (e==null) return null;
        return new Role(e.getId(), e.getNombre(), e.getDescripcion(), e.getCreatedAt(), e.getUpdatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Role toEntity(Role d) {
        if (d==null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Role e = new efinomina.message.efinomina.infraestructure.persistence.entity.Role();
        e.setId(d.getId());
        e.setNombre(d.getNombre());
        e.setDescripcion(d.getDescripcion());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }

    static RoleDTO toDTO(Role d) {
        if (d==null) return null;
        return new RoleDTO(d.getId(), d.getNombre(), d.getDescripcion(), d.getCreatedAt(), d.getUpdatedAt());
    }

    static Role toDomainFromDTO(RoleDTO dto) {
        if (dto==null) return null;
        return new Role(dto.getId(), dto.getNombre(), dto.getDescripcion(), dto.getCreatedAt(), dto.getUpdatedAt());
    }
}
