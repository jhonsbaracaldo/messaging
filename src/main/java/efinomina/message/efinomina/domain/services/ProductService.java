package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.Product;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryProduct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final RepositoryProduct repository;

    public ProductService(RepositoryProduct repository) {
        this.repository = repository;
    }

    public Product save(Product product) {
        product.setActive(true);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return repository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findAllActive() {
        return repository.findByActiveTrue();
    }

    public List<Product> findByCategory(Long categoryId) {
        return repository.findByCategoryId(categoryId);
    }

    public List<Product> findLowStock() {
        return repository.findAll().stream()
                .filter(p -> p.getStock() != null && p.getMinimumStock() != null
                        && p.getStock() <= p.getMinimumStock())
                .toList();
    }

    public Product update(Long id, Product product) {
        return repository.findById(id).map(existing -> {
            existing.setName(product.getName());
            existing.setDescription(product.getDescription());
            existing.setBarcode(product.getBarcode());
            existing.setPrice(product.getPrice());
            existing.setStock(product.getStock());
            existing.setMinimumStock(product.getMinimumStock());
            existing.setActive(product.getActive());
            existing.setUpdatedAt(LocalDateTime.now());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
