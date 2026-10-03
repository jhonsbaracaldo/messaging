package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryStockMovement extends JpaRepository<StockMovement, Long> {
    List<StockMovement> findByProductId(Long productId);
    List<StockMovement> findByMovementType(String movementType);
}
