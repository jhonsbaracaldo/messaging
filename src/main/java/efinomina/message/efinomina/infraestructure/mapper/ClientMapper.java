package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.ClientDTO;
import efinomina.message.efinomina.domain.model.entity.Client;

public interface ClientMapper {

    static Client toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Client e) {
        if (e == null) return null;
        return new Client(e.getId(), e.getName(), e.getLastName(), e.getPhone(), e.getEmail(), e.getNotes(), e.getActive(), e.getCreatedAt(), e.getPreferredBarberId());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Client toEntity(Client d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Client e = new efinomina.message.efinomina.infraestructure.persistence.entity.Client();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setLastName(d.getLastName());
        e.setPhone(d.getPhone());
        e.setEmail(d.getEmail());

        e.setNotes(d.getNotes());
        e.setActive(d.getActive());
        e.setCreatedAt(d.getCreatedAt());
        e.setPreferredBarberId(d.getPreferredBarberId());
        return e;
    }

    static ClientDTO toDTO(Client d) {
        if (d == null) return null;
        return new ClientDTO(d.getId(), d.getName(), d.getLastName(), d.getPhone(), d.getEmail(), d.getNotes(), d.getActive(), d.getCreatedAt(), d.getPreferredBarberId());
    }

    static Client toDomainFromDTO(ClientDTO dto) {
        if (dto == null) return null;
        return new Client(dto.getId(), dto.getName(), dto.getLastName(), dto.getPhone(), dto.getEmail(), dto.getNotes(), dto.getActive(), dto.getCreatedAt(), dto.getBarberId());
    }
}
