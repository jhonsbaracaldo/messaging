package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.StockMovement;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryStockMovement;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockMovementService {

    private final RepositoryStockMovement repository;

    public StockMovementService(RepositoryStockMovement repository) {
        this.repository = repository;
    }

    public StockMovement save(StockMovement movement) {
        movement.setCreatedAt(LocalDateTime.now());
        return repository.save(movement);
    }

    public Optional<StockMovement> findById(Long id) {
        return repository.findById(id);
    }

    public List<StockMovement> findAll() {
        return repository.findAll();
    }

    public List<StockMovement> findByProduct(Long productId) {
        return repository.findByProductId(productId);
    }

    public List<StockMovement> findByType(String movementType) {
        return repository.findByMovementType(movementType);
    }
}
