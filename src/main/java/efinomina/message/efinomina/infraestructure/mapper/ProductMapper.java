package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.ProductDTO;
import efinomina.message.efinomina.domain.model.entity.Product;

public interface ProductMapper {

    static Product toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Product e) {
        if (e == null) return null;
        Long categoryId = e.getCategory() != null ? e.getCategory().getId() : null;
        return new Product(e.getId(), e.getName(), e.getDescription(), e.getBarcode(), categoryId, e.getPrice(), e.getStock(), e.getMinimumStock(), e.getActive(), e.getCreatedAt(), e.getUpdatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Product toEntity(Product d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Product e = new efinomina.message.efinomina.infraestructure.persistence.entity.Product();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setDescription(d.getDescription());
        e.setBarcode(d.getBarcode());
        if (d.getCategoryId() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.ProductCategory cat = new efinomina.message.efinomina.infraestructure.persistence.entity.ProductCategory();
            cat.setId(d.getCategoryId());
            e.setCategory(cat);
        }
        e.setPrice(d.getPrice());
        e.setStock(d.getStock());
        e.setMinimumStock(d.getMinimumStock());
        e.setActive(d.getActive());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }

    static ProductDTO toDTO(Product d) {
        if (d == null) return null;
        return new ProductDTO(d.getId(), d.getName(), d.getDescription(), d.getBarcode(), d.getCategoryId(), d.getPrice(), d.getStock(), d.getMinimumStock(), d.getActive(), d.getCreatedAt(), d.getUpdatedAt());
    }

    static Product toDomainFromDTO(ProductDTO dto) {
        if (dto == null) return null;
        return new Product(dto.getId(), dto.getName(), dto.getDescription(), dto.getBarcode(), dto.getCategoryId(), dto.getPrice(), dto.getStock(), dto.getMinimumStock(), dto.getActive(), dto.getCreatedAt(), dto.getUpdatedAt());
    }
}
