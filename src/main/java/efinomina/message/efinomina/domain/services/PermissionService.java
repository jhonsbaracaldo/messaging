package efinomina.message.efinomina.domain.services;

import efinomina.message.efinomina.aplication.dto.PermissionDto;

import efinomina.message.efinomina.domain.exception.BadRequestException;
import efinomina.message.efinomina.domain.exception.NotFoundException;
import efinomina.message.efinomina.domain.model.entity.Permission;
import efinomina.message.efinomina.infraestructure.mapper.PermissionMapper;
import java.util.List;
import java.util.Optional;

import efinomina.message.efinomina.infraestructure.persistence.entity.PermissionEntity;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryPermission;

import org.springframework.stereotype.Service;



@Service
public class PermissionService {

    private final RepositoryPermission repositoryPermission;
    private final PermissionMapper permissionMapper;

    public PermissionService(RepositoryPermission permission, PermissionMapper permissionMapper) {
        this.repositoryPermission = permission;
        this.permissionMapper = permissionMapper;
    }

    public PermissionDto createPermission(PermissionDto dto) {

        if (dto.getCode() == null || dto.getCode().isEmpty()) {
            throw new BadRequestException(BadRequestException.CODIGO_REQUERIDO);
        }
            Permission domainPermission = permissionMapper.toDomainFromDTO(dto);
            PermissionEntity entity = permissionMapper.toEntity(domainPermission);
            PermissionEntity saved = repositoryPermission.save(entity);
            return permissionMapper.toDTO(permissionMapper.toDomain(saved));

    }

    public List<PermissionDto> findAllPermission() {

        List<PermissionDto> permissions = repositoryPermission.findAll()

                 .stream()
                .map(permissionMapper::toDomain)         // PermissionEntity → PermissionController
                .map(permissionMapper::toDTO)            // PermissionController → PermissionDto
                .toList();       // arma la lista


     return permissions;
    }

    public Optional<PermissionDto> deletePermission(Integer id) {
        Optional<PermissionEntity> permissionEntity = repositoryPermission.findById(id);
        if (permissionEntity.isPresent()) {
            repositoryPermission.delete(permissionEntity.get());
            return Optional.of(permissionMapper.toDTO(permissionMapper.toDomain(permissionEntity.get())));
        } else {
            return Optional.empty();
        }
    }

    public PermissionDto UpdatePermission(Integer id, PermissionDto dto) {
        Optional<PermissionEntity> permissionEntity = repositoryPermission.findById(id);
        if (permissionEntity.isPresent()) {
            PermissionEntity entityToUpdate = permissionEntity.get();
            entityToUpdate.setName(dto.getName());
            entityToUpdate.setDescription(dto.getDescription());
            PermissionEntity updated = repositoryPermission.save(entityToUpdate);
            return permissionMapper.toDTO(permissionMapper.toDomain(updated));
        } else {
            throw new RuntimeException("PermissionController not found with id: " + id);
        }
    }

}
