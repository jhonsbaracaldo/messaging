package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.UserDTO;
import efinomina.message.efinomina.domain.model.entity.User;

public interface UserMapper {

    static User toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.User e) {
        if (e == null) return null;
        return new User(e.getId(), e.getName(), e.getLastName(), e.getEmail(),
                e.getPassword(), e.getPhone(), e.getPhotoUrl(),
                e.getActive(), e.getLastLogin(), e.getCreatedAt(), e.getUpdatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.User toEntity(User d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.User e =
                new efinomina.message.efinomina.infraestructure.persistence.entity.User();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setLastName(d.getLastName());
        e.setEmail(d.getEmail());
        e.setPassword(d.getPassword());
        e.setPhone(d.getPhone());
        e.setPhotoUrl(d.getPhotoUrl());
        e.setActive(d.getActive());
        e.setLastLogin(d.getLastLogin());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }

    static UserDTO toDTO(User d) {
        if (d == null) return null;
        return new UserDTO(d.getId(), d.getName(), d.getLastName(), d.getEmail(),
                d.getPassword(), d.getPhone(), d.getPhotoUrl(),
                d.getActive(), d.getLastLogin(), d.getCreatedAt(), d.getUpdatedAt());
    }

    static User toDomainFromDTO(UserDTO dto) {
        if (dto == null) return null;
        return new User(dto.getId(), dto.getName(), dto.getLastName(), dto.getEmail(),
                dto.getPassword(), dto.getPhone(), dto.getPhotoUrl(),
                dto.getActive(), dto.getLastLogin(), dto.getCreatedAt(), dto.getUpdatedAt());
    }
}
