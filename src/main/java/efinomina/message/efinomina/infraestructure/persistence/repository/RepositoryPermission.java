package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryPermission extends JpaRepository<PermissionEntity, Integer> {



}
