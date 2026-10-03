package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.PermissionDto;

import efinomina.message.efinomina.domain.model.entity.Permission;
import efinomina.message.efinomina.infraestructure.persistence.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PermissionMapper {


    //JPA->Domain
    @Mapping(target = "active", defaultValue = "true")
    @Mapping(target = "created_at", ignore = true)
    Permission toDomain(PermissionEntity entity);

    // Dominio → JPA ✅
    PermissionEntity toEntity(Permission domain);

    // Dominio → DTO
    PermissionDto toDTO(Permission domain);

    // DTO → Dominio
    Permission toDomainFromDTO(PermissionDto dto);

}
