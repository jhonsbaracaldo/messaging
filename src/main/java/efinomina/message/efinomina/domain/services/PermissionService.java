package efinomina.message.efinomina.domain.services;

import efinomina.message.efinomina.aplication.dto.PermissionDto;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryPermission;
import org.springframework.stereotype.Service;

@Service
public class Permission {

    private final RepositoryPermission repositoryPermission;

    public Permission(RepositoryPermission permission) {
        this.repositoryPermission = permission;
    }

    public String CreatePermission(PermissionDto permissionDto){
        return  repositoryPermission.save
    }
}
