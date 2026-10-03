package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.RegisterClientDTO;
import efinomina.message.efinomina.domain.model.entity.RegisterClient;

public interface RegisterClientMapper {

    static RegisterClient toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.RegisterClient e) {
        if (e==null) return null;
        return new RegisterClient(e.getIdCliente(), e.getNombre(), e.getApellido(), e.getCorreo(), e.getTelefono());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.RegisterClient toEntity(RegisterClient d) {
        if (d==null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.RegisterClient e = new efinomina.message.efinomina.infraestructure.persistence.entity.RegisterClient();
        e.setIdCliente(d.getIdCliente());
        e.setNombre(d.getNombre());
        e.setApellido(d.getApellido());
        e.setCorreo(d.getCorreo());
        e.setTelefono(d.getTelefono());
        return e;
    }

    static RegisterClientDTO toDTO(RegisterClient d) {
        if (d==null) return null;
        return new RegisterClientDTO(d.getIdCliente(), d.getNombre(), d.getApellido(), d.getCorreo(), d.getTelefono());
    }

    static RegisterClient toDomainFromDTO(RegisterClientDTO dto) {
        if (dto==null) return null;
        return new RegisterClient(dto.getIdCliente(), dto.getNombre(), dto.getApellido(), dto.getCorreo(), dto.getTelefono());
    }
}

