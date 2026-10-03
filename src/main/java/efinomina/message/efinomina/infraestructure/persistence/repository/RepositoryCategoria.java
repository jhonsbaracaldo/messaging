package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryCategoria extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findById(Integer id);
    Optional<Categoria> findByNombre(String nombre);
}

