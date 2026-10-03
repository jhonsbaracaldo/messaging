package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.CashMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryCashMovement extends JpaRepository<CashMovement, Long> {
    List<CashMovement> findByType(String type);
}
