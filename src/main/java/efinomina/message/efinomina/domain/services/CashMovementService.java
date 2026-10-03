package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.CashMovement;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryCashMovement;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CashMovementService {

    private final RepositoryCashMovement repository;

    public CashMovementService(RepositoryCashMovement repository) {
        this.repository = repository;
    }

    public CashMovement save(CashMovement movement) {
        movement.setCreatedAt(LocalDateTime.now());
        return repository.save(movement);
    }

    public Optional<CashMovement> findById(Long id) {
        return repository.findById(id);
    }

    public List<CashMovement> findAll() {
        return repository.findAll();
    }

    public List<CashMovement> findByType(String type) {
        return repository.findByType(type);
    }
}
