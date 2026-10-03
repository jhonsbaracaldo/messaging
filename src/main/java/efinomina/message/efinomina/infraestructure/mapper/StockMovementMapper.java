package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.StockMovementDTO;
import efinomina.message.efinomina.domain.model.entity.StockMovement;

public interface StockMovementMapper {

    static StockMovement toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.StockMovement e) {
        if (e == null) return null;
        Long productId = e.getProduct() != null ? e.getProduct().getId() : null;
        Long createdById = e.getCreatedBy() != null ? e.getCreatedBy().getId() : null;
        return new StockMovement(e.getId(), productId, e.getMovementType(), e.getQuantity(), e.getPreviousStock(), e.getNewStock(), e.getReason(), createdById, e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.StockMovement toEntity(StockMovement d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.StockMovement e = new efinomina.message.efinomina.infraestructure.persistence.entity.StockMovement();
        e.setId(d.getId());
        if (d.getProductId() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Product product = new efinomina.message.efinomina.infraestructure.persistence.entity.Product();
            product.setId(d.getProductId());
            e.setProduct(product);
        }
        if (d.getCreatedById() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.User user = new efinomina.message.efinomina.infraestructure.persistence.entity.User();
            user.setId(d.getCreatedById());
            e.setCreatedBy(user);
        }
        e.setMovementType(d.getMovementType());
        e.setQuantity(d.getQuantity());
        e.setPreviousStock(d.getPreviousStock());
        e.setNewStock(d.getNewStock());
        e.setReason(d.getReason());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    static StockMovementDTO toDTO(StockMovement d) {
        if (d == null) return null;
        return new StockMovementDTO(d.getId(), d.getProductId(), d.getMovementType(), d.getQuantity(), d.getPreviousStock(), d.getNewStock(), d.getReason(), d.getCreatedById(), d.getCreatedAt());
    }

    static StockMovement toDomainFromDTO(StockMovementDTO dto) {
        if (dto == null) return null;
        return new StockMovement(dto.getId(), dto.getProductId(), dto.getMovementType(), dto.getQuantity(), dto.getPreviousStock(), dto.getNewStock(), dto.getReason(), dto.getCreatedById(), dto.getCreatedAt());
    }
}
