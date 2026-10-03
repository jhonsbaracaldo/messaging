package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.Supplier;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositorySupplier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final RepositorySupplier repository;

    public SupplierService(RepositorySupplier repository) {
        this.repository = repository;
    }

    public Supplier save(Supplier supplier) {
        supplier.setActive(true);
        supplier.setCreatedAt(LocalDateTime.now());
        return repository.save(supplier);
    }

    public Optional<Supplier> findById(Long id) {
        return repository.findById(id);
    }

    public List<Supplier> findAll() {
        return repository.findAll();
    }

    public List<Supplier> findAllActive() {
        return repository.findByActiveTrue();
    }

    public Supplier update(Long id, Supplier supplier) {
        return repository.findById(id).map(existing -> {
            existing.setName(supplier.getName());
            existing.setPhone(supplier.getPhone());
            existing.setEmail(supplier.getEmail());
            existing.setCompany(supplier.getCompany());
            existing.setAddress(supplier.getAddress());
            existing.setActive(supplier.getActive());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Proveedor no encontrado: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
