package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.ServicioDTO;
import efinomina.message.efinomina.domain.model.entity.Servicio;

public interface ServicioMapper {

    static Servicio toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Servicio e) {
        if (e == null) return null;
        return new Servicio(e.getId(), e.getName(), e.getDescription(), e.getPrice(), e.getDurationMinutes(), e.getActive(), e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Servicio toEntity(Servicio d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Servicio e = new efinomina.message.efinomina.infraestructure.persistence.entity.Servicio();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setDescription(d.getDescription());
        e.setPrice(d.getPrice());
        e.setDurationMinutes(d.getDurationMinutes());
        e.setActive(d.getActive());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    static ServicioDTO toDTO(Servicio d) {
        if (d == null) return null;
        return new ServicioDTO(d.getId(), d.getName(), d.getDescription(), d.getPrice(), d.getDurationMinutes(), d.getActive(), d.getCreatedAt());
    }

    static Servicio toDomainFromDTO(ServicioDTO dto) {
        if (dto == null) return null;
        return new Servicio(dto.getId(), dto.getName(), dto.getDescription(), dto.getPrice(), dto.getDurationMinutes(), dto.getActive(), dto.getCreatedAt());
    }
}
