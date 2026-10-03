package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.SupplierDTO;
import efinomina.message.efinomina.domain.model.entity.Supplier;

public interface SupplierMapper {

    static Supplier toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Supplier e) {
        if (e == null) return null;
        return new Supplier(e.getId(), e.getName(), e.getPhone(), e.getEmail(), e.getCompany(), e.getAddress(), e.getActive(), e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Supplier toEntity(Supplier d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Supplier e = new efinomina.message.efinomina.infraestructure.persistence.entity.Supplier();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setPhone(d.getPhone());
        e.setEmail(d.getEmail());
        e.setCompany(d.getCompany());
        e.setAddress(d.getAddress());
        e.setActive(d.getActive());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    static SupplierDTO toDTO(Supplier d) {
        if (d == null) return null;
        return new SupplierDTO(d.getId(), d.getName(), d.getPhone(), d.getEmail(), d.getCompany(), d.getAddress(), d.getActive(), d.getCreatedAt());
    }

    static Supplier toDomainFromDTO(SupplierDTO dto) {
        if (dto == null) return null;
        return new Supplier(dto.getId(), dto.getName(), dto.getPhone(), dto.getEmail(), dto.getCompany(), dto.getAddress(), dto.getActive(), dto.getCreatedAt());
    }
}
