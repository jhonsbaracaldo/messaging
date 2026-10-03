package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.RegisterClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryRegisterClient extends JpaRepository<RegisterClient, Integer> {
    Optional<RegisterClient> findByIdCliente(Integer idCliente);
    Optional<RegisterClient> findByCorreo(String correo);
}

