package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.ProductCategory;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryProductCategory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    private final RepositoryProductCategory repository;

    public ProductCategoryService(RepositoryProductCategory repository) {
        this.repository = repository;
    }

    public ProductCategory save(ProductCategory category) {
        category.setActive(true);
        category.setCreatedAt(LocalDateTime.now());
        return repository.save(category);
    }

    public Optional<ProductCategory> findById(Long id) {
        return repository.findById(id);
    }

    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    public List<ProductCategory> findAllActive() {
        return repository.findByActiveTrue();
    }

    public ProductCategory update(Long id, ProductCategory category) {
        return repository.findById(id).map(existing -> {
            existing.setName(category.getName());
            existing.setDescription(category.getDescription());
            existing.setActive(category.getActive());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
