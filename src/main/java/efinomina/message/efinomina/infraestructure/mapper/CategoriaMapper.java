package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.CategoriaDTO;
import efinomina.message.efinomina.domain.model.entity.Categoria;

public interface CategoriaMapper {

    static Categoria toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Categoria e) {
        if (e==null) return null;
        return new Categoria(e.getId(), e.getNombre(), e.getDescripcion(), e.getCreatedAt(), e.getUpdatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Categoria toEntity(Categoria d) {
        if (d==null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Categoria e = new efinomina.message.efinomina.infraestructure.persistence.entity.Categoria();
        e.setId(d.getId());
        e.setNombre(d.getNombre());
        e.setDescripcion(d.getDescripcion());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }

    static CategoriaDTO toDTO(Categoria d) {
        if (d==null) return null;
        return new CategoriaDTO(d.getId(), d.getNombre(), d.getDescripcion(), d.getCreatedAt(), d.getUpdatedAt());
    }

    static Categoria toDomainFromDTO(CategoriaDTO dto) {
        if (dto==null) return null;
        return new Categoria(dto.getId(), dto.getNombre(), dto.getDescripcion(), dto.getCreatedAt(), dto.getUpdatedAt());
    }
}
