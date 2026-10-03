package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.ProductCategoryDTO;
import efinomina.message.efinomina.domain.model.entity.ProductCategory;

public interface ProductCategoryMapper {

    static ProductCategory toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.ProductCategory e) {
        if (e == null) return null;
        return new ProductCategory(e.getId(), e.getName(), e.getDescription(), e.getActive(), e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.ProductCategory toEntity(ProductCategory d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.ProductCategory e = new efinomina.message.efinomina.infraestructure.persistence.entity.ProductCategory();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setDescription(d.getDescription());
        e.setActive(d.getActive());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    static ProductCategoryDTO toDTO(ProductCategory d) {
        if (d == null) return null;
        return new ProductCategoryDTO(d.getId(), d.getName(), d.getDescription(), d.getActive(), d.getCreatedAt());
    }

    static ProductCategory toDomainFromDTO(ProductCategoryDTO dto) {
        if (dto == null) return null;
        return new ProductCategory(dto.getId(), dto.getName(), dto.getDescription(), dto.getActive(), dto.getCreatedAt());
    }
}
