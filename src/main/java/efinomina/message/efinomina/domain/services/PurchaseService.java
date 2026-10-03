package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.Purchase;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryPurchase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final RepositoryPurchase repository;

    public PurchaseService(RepositoryPurchase repository) {
        this.repository = repository;
    }

    public Purchase save(Purchase purchase) {
        purchase.setCreatedAt(LocalDateTime.now());
        return repository.save(purchase);
    }

    public Optional<Purchase> findById(Long id) {
        return repository.findById(id);
    }

    public List<Purchase> findAll() {
        return repository.findAll();
    }

    public List<Purchase> findBySupplier(Long supplierId) {
        return repository.findBySupplierId(supplierId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
