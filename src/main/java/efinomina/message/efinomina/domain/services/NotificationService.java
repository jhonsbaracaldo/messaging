package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.Notification;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryNotification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final RepositoryNotification repository;

    public NotificationService(RepositoryNotification repository) {
        this.repository = repository;
    }

    public Notification save(Notification notification) {
        notification.setIsRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        return repository.save(notification);
    }

    public Optional<Notification> findById(Long id) {
        return repository.findById(id);
    }

    public List<Notification> findByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<Notification> findUnreadByUser(Long userId) {
        return repository.findByUserIdAndIsReadFalse(userId);
    }

    public Notification markAsRead(Long id) {
        return repository.findById(id).map(existing -> {
            existing.setIsRead(true);
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Notificación no encontrada: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
