package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryProducto extends JpaRepository<Producto, Integer> {
    Optional<Producto> findById(Integer id);
    Optional<Producto> findByCodigo(String codigo);
}

