package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryRole extends JpaRepository<Role, Integer> {
    Optional<Role> findById(Integer id);
    Optional<Role> findByNombre(String nombre);
}

