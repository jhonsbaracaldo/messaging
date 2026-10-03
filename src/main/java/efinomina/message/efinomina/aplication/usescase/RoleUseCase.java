package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.RoleDTO;
import efinomina.message.efinomina.domain.model.entity.Role;
import efinomina.message.efinomina.infraestructure.mapper.RoleMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryRole;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleUseCase {
    private final RepositoryRole repositoryRole;

    public RoleUseCase(RepositoryRole repositoryRole) {
        this.repositoryRole = repositoryRole;
    }

    public RoleDTO crearRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setNombre(roleDTO.getNombre());
        role.setDescripcion(roleDTO.getDescripcion());
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());

        efinomina.message.efinomina.infraestructure.persistence.entity.Role roleEntity =
            RoleMapper.toEntity(role);
        efinomina.message.efinomina.infraestructure.persistence.entity.Role roleSaved =
            repositoryRole.save(roleEntity);
        return RoleMapper.toDTO(RoleMapper.toDomain(roleSaved));
    }

    public Optional<RoleDTO> obtenerRolePorId(Integer id) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Role> role =
            repositoryRole.findById(id);
        return role.map(r -> RoleMapper.toDTO(RoleMapper.toDomain(r)));
    }

    public List<RoleDTO> obtenerTodosLosRoles() {
        return repositoryRole.findAll()
                .stream()
                .map(r -> RoleMapper.toDTO(RoleMapper.toDomain(r)))
                .collect(Collectors.toList());
    }

    public RoleDTO actualizarRole(Integer id, RoleDTO roleDTO) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Role> roleExistente =
            repositoryRole.findById(id);

        if (roleExistente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Role role = roleExistente.get();
            role.setNombre(roleDTO.getNombre());
            role.setDescripcion(roleDTO.getDescripcion());
            role.setUpdatedAt(LocalDateTime.now());

            efinomina.message.efinomina.infraestructure.persistence.entity.Role roleActualizado =
                repositoryRole.save(role);
            return RoleMapper.toDTO(RoleMapper.toDomain(roleActualizado));
        }
        return null;
    }

    public void eliminarRole(Integer id) {
        repositoryRole.deleteById(id);
    }
}

