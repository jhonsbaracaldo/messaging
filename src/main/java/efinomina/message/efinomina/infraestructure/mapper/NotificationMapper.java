package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.NotificationDTO;
import efinomina.message.efinomina.domain.model.entity.Notification;

public interface NotificationMapper {

    static Notification toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Notification e) {
        if (e == null) return null;
        Long userId = e.getUser() != null ? e.getUser().getId() : null;
        return new Notification(e.getId(), userId, e.getTitle(), e.getMessage(), e.getType(), e.getIsRead(), e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Notification toEntity(Notification d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Notification e = new efinomina.message.efinomina.infraestructure.persistence.entity.Notification();
        e.setId(d.getId());
        if (d.getUserId() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.User user = new efinomina.message.efinomina.infraestructure.persistence.entity.User();
            user.setId(d.getUserId());
            e.setUser(user);
        }
        e.setTitle(d.getTitle());
        e.setMessage(d.getMessage());
        e.setType(d.getType());
        e.setIsRead(d.getIsRead());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    static NotificationDTO toDTO(Notification d) {
        if (d == null) return null;
        return new NotificationDTO(d.getId(), d.getUserId(), d.getTitle(), d.getMessage(), d.getType(), d.getIsRead(), d.getCreatedAt());
    }

    static Notification toDomainFromDTO(NotificationDTO dto) {
        if (dto == null) return null;
        return new Notification(dto.getId(), dto.getUserId(), dto.getTitle(), dto.getMessage(), dto.getType(), dto.getIsRead(), dto.getCreatedAt());
    }
}
