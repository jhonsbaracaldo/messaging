package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.AppointmentDTO;
import efinomina.message.efinomina.domain.model.entity.Appointment;

public interface AppointmentMapper {

    static Appointment toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Appointment e) {
        if (e == null) return null;
        Long clientId = e.getClient() != null ? e.getClient().getId() : null;
        Long barberId = e.getBarber() != null ? e.getBarber().getId() : null;
        Long createdById = e.getCreatedBy() != null ? e.getCreatedBy().getId() : null;
        return new Appointment(e.getId(), clientId, barberId, e.getAppointmentDate(), e.getStartTime(), e.getEndTime(), e.getStatus(), e.getNotes(), e.getTotal(), createdById, e.getCreatedAt(), e.getUpdatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Appointment toEntity(Appointment d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Appointment e = new efinomina.message.efinomina.infraestructure.persistence.entity.Appointment();
        e.setId(d.getId());
        if (d.getClientId() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Client client = new efinomina.message.efinomina.infraestructure.persistence.entity.Client();
            client.setId(d.getClientId());
            e.setClient(client);
        }
        if (d.getBarberId() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Barber barber = new efinomina.message.efinomina.infraestructure.persistence.entity.Barber();
            barber.setId(d.getBarberId());
            e.setBarber(barber);
        }
        if (d.getCreatedById() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.User user = new efinomina.message.efinomina.infraestructure.persistence.entity.User();
            user.setId(d.getCreatedById());
            e.setCreatedBy(user);
        }
        e.setAppointmentDate(d.getAppointmentDate());
        e.setStartTime(d.getStartTime());
        e.setEndTime(d.getEndTime());
        e.setStatus(d.getStatus());
        e.setNotes(d.getNotes());
        e.setTotal(d.getTotal());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }

    static AppointmentDTO toDTO(Appointment d) {
        if (d == null) return null;
        return new AppointmentDTO(d.getId(), d.getClientId(), d.getBarberId(), d.getAppointmentDate(), d.getStartTime(), d.getEndTime(), d.getStatus(), d.getNotes(), d.getTotal(), d.getCreatedById(), d.getCreatedAt(), d.getUpdatedAt());
    }

    static Appointment toDomainFromDTO(AppointmentDTO dto) {
        if (dto == null) return null;
        return new Appointment(dto.getId(), dto.getClientId(), dto.getBarberId(), dto.getAppointmentDate(), dto.getStartTime(), dto.getEndTime(), dto.getStatus(), dto.getNotes(), dto.getTotal(), dto.getCreatedById(), dto.getCreatedAt(), dto.getUpdatedAt());
    }
}
