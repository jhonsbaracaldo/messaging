package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.PurchaseDTO;
import efinomina.message.efinomina.domain.model.entity.Purchase;

public interface PurchaseMapper {

    static Purchase toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Purchase e) {
        if (e == null) return null;
        Long supplierId = e.getSupplier() != null ? e.getSupplier().getId() : null;
        Long createdById = e.getCreatedBy() != null ? e.getCreatedBy().getId() : null;
        return new Purchase(e.getId(), supplierId, e.getTotal(), e.getInvoiceNumber(), createdById, e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Purchase toEntity(Purchase d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Purchase e = new efinomina.message.efinomina.infraestructure.persistence.entity.Purchase();
        e.setId(d.getId());
        if (d.getSupplierId() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Supplier supplier = new efinomina.message.efinomina.infraestructure.persistence.entity.Supplier();
            supplier.setId(d.getSupplierId());
            e.setSupplier(supplier);
        }
        if (d.getCreatedById() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.User user = new efinomina.message.efinomina.infraestructure.persistence.entity.User();
            user.setId(d.getCreatedById());
            e.setCreatedBy(user);
        }
        e.setTotal(d.getTotal());
        e.setInvoiceNumber(d.getInvoiceNumber());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    static PurchaseDTO toDTO(Purchase d) {
        if (d == null) return null;
        return new PurchaseDTO(d.getId(), d.getSupplierId(), d.getTotal(), d.getInvoiceNumber(), d.getCreatedById(), d.getCreatedAt());
    }

    static Purchase toDomainFromDTO(PurchaseDTO dto) {
        if (dto == null) return null;
        return new Purchase(dto.getId(), dto.getSupplierId(), dto.getTotal(), dto.getInvoiceNumber(), dto.getCreatedById(), dto.getCreatedAt());
    }
}
