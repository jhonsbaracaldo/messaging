package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.NotificationDTO;
import efinomina.message.efinomina.domain.model.entity.Notification;
import efinomina.message.efinomina.infraestructure.mapper.NotificationMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryNotification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationUseCase {

    private final RepositoryNotification repositoryNotification;

    public NotificationUseCase(RepositoryNotification repositoryNotification) {
        this.repositoryNotification = repositoryNotification;
    }

    public NotificationDTO crearNotificacion(NotificationDTO dto) {
        Notification n = new Notification();
        n.setUserId(dto.getUserId());
        n.setTitle(dto.getTitle());
        n.setMessage(dto.getMessage());
        n.setType(dto.getType());
        n.setIsRead(false);
        n.setCreatedAt(LocalDateTime.now());
        return NotificationMapper.toDTO(NotificationMapper.toDomain(repositoryNotification.save(NotificationMapper.toEntity(n))));
    }

    public Optional<NotificationDTO> obtenerNotificacionPorId(Long id) {
        return repositoryNotification.findById(id).map(e -> NotificationMapper.toDTO(NotificationMapper.toDomain(e)));
    }

    public List<NotificationDTO> obtenerTodasLasNotificaciones() {
        return repositoryNotification.findAll().stream()
                .map(e -> NotificationMapper.toDTO(NotificationMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public void eliminarNotificacion(Long id) {
        repositoryNotification.deleteById(id);
    }

    public List<NotificationDTO> obtenerNotificacionesPorUsuario(Long userId) {
        return repositoryNotification.findByUserId(userId).stream()
                .map(e -> NotificationMapper.toDTO(NotificationMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<NotificationDTO> obtenerNotificacionesNoLeidasPorUsuario(Long userId) {
        return repositoryNotification.findByUserIdAndIsReadFalse(userId).stream()
                .map(e -> NotificationMapper.toDTO(NotificationMapper.toDomain(e)))
                .collect(Collectors.toList());
    }
}
