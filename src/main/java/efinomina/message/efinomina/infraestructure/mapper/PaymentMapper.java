package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.PaymentDTO;
import efinomina.message.efinomina.domain.model.entity.Payment;

public interface PaymentMapper {

    static Payment toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Payment e) {
        if (e == null) return null;
        Long appointmentId = e.getAppointment() != null ? e.getAppointment().getId() : null;
        Long createdById = e.getCreatedBy() != null ? e.getCreatedBy().getId() : null;
        return new Payment(e.getId(), appointmentId, e.getTotal(), e.getPaymentMethod(), e.getPaymentStatus(), e.getTransactionReference(), createdById, e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Payment toEntity(Payment d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Payment e = new efinomina.message.efinomina.infraestructure.persistence.entity.Payment();
        e.setId(d.getId());
        if (d.getAppointmentId() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Appointment apt = new efinomina.message.efinomina.infraestructure.persistence.entity.Appointment();
            apt.setId(d.getAppointmentId());
            e.setAppointment(apt);
        }
        if (d.getCreatedById() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.User user = new efinomina.message.efinomina.infraestructure.persistence.entity.User();
            user.setId(d.getCreatedById());
            e.setCreatedBy(user);
        }
        e.setTotal(d.getTotal());
        e.setPaymentMethod(d.getPaymentMethod());
        e.setPaymentStatus(d.getPaymentStatus());
        e.setTransactionReference(d.getTransactionReference());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    static PaymentDTO toDTO(Payment d) {
        if (d == null) return null;
        return new PaymentDTO(d.getId(), d.getAppointmentId(), d.getTotal(), d.getPaymentMethod(), d.getPaymentStatus(), d.getTransactionReference(), d.getCreatedById(), d.getCreatedAt());
    }

    static Payment toDomainFromDTO(PaymentDTO dto) {
        if (dto == null) return null;
        return new Payment(dto.getId(), dto.getAppointmentId(), dto.getTotal(), dto.getPaymentMethod(), dto.getPaymentStatus(), dto.getTransactionReference(), dto.getCreatedById(), dto.getCreatedAt());
    }
}
