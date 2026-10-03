package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.BarberDTO;
import efinomina.message.efinomina.domain.model.entity.Barber;

public interface BarberMapper {

    static Barber toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Barber e) {
        if (e == null) return null;
        Long userId = e.getUser() != null ? e.getUser().getId() : null;
        String name = e.getUser() != null ? (e.getUser().getName() + " " + e.getUser().getLastName()) : null;
        return new Barber(e.getId(), userId, name, e.getPhone(), e.getSpecialty(), e.getExperienceYears(), e.getDescription(), e.getPhotoUrl(), e.getActive(), e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Barber toEntity(Barber d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Barber e = new efinomina.message.efinomina.infraestructure.persistence.entity.Barber();
        e.setId(d.getId());
        if (d.getUserId() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.User user = new efinomina.message.efinomina.infraestructure.persistence.entity.User();
            user.setId(d.getUserId());
            e.setUser(user);
        }
        e.setSpecialty(d.getSpecialty());
        e.setExperienceYears(d.getExperienceYears());
        e.setDescription(d.getDescription());
        e.setPhotoUrl(d.getPhotoUrl());
        e.setActive(d.getActive());
        e.setCreatedAt(d.getCreatedAt());
        e.setPhone(d.getPhone());
        return e;
    }

    static BarberDTO toDTO(Barber d) {
        if (d == null) return null;
        return new BarberDTO(d.getId(), d.getUserId(), d.getName(), d.getPhone(), d.getSpecialty(), d.getExperienceYears(), d.getDescription(), d.getPhotoUrl(), d.getActive(), d.getCreatedAt());
    }

    static Barber toDomainFromDTO(BarberDTO dto) {
        if (dto == null) return null;
        return new Barber(dto.getId(), dto.getUserId(), dto.getName(), dto.getPhone(), dto.getSpecialty(), dto.getExperienceYears(), dto.getDescription(), dto.getPhotoUrl(), dto.getActive(), dto.getCreatedAt());
    }
}
