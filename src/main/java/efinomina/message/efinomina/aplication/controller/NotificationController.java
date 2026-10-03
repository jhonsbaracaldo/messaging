package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.NotificationDTO;
import efinomina.message.efinomina.aplication.usescase.NotificationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificationController {

    private final NotificationUseCase notificationUseCase;

    public NotificationController(NotificationUseCase notificationUseCase) {
        this.notificationUseCase = notificationUseCase;
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> crear(@RequestBody NotificationDTO dto) {
        return new ResponseEntity<>(notificationUseCase.crearNotificacion(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> obtenerPorId(@PathVariable Long id) {
        Optional<NotificationDTO> result = notificationUseCase.obtenerNotificacionPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> obtenerTodas() {
        return ResponseEntity.ok(notificationUseCase.obtenerTodasLasNotificaciones());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificationUseCase.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<NotificationDTO>> obtenerPorUsuario(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationUseCase.obtenerNotificacionesPorUsuario(userId));
    }

    @GetMapping("/usuario/{userId}/no-leidas")
    public ResponseEntity<List<NotificationDTO>> obtenerNoLeidasPorUsuario(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationUseCase.obtenerNotificacionesNoLeidasPorUsuario(userId));
    }
}
