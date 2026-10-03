package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.AppointmentDTO;
import efinomina.message.efinomina.aplication.usescase.AppointmentUseCase;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citas")
public class AppointmentController {

    private final AppointmentUseCase appointmentUseCase;

    public AppointmentController(AppointmentUseCase appointmentUseCase) {
        this.appointmentUseCase = appointmentUseCase;
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> crear(@RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO creado = appointmentUseCase.crearCita(appointmentDTO);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> obtenerPorId(@PathVariable Long id) {
        Optional<AppointmentDTO> cita = appointmentUseCase.obtenerCitaPorId(id);
        return cita.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> obtenerTodas() {
        return ResponseEntity.ok(appointmentUseCase.obtenerTodasLasCitas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> actualizar(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO actualizado = appointmentUseCase.actualizarCita(id, appointmentDTO);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        appointmentUseCase.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clientId}")
    public ResponseEntity<List<AppointmentDTO>> obtenerPorCliente(@PathVariable Long clientId) {
        return ResponseEntity.ok(appointmentUseCase.obtenerCitasPorCliente(clientId));
    }

    @GetMapping("/barbero/{barberId}")
    public ResponseEntity<List<AppointmentDTO>> obtenerPorBarbero(@PathVariable Long barberId) {
        return ResponseEntity.ok(appointmentUseCase.obtenerCitasPorBarbero(barberId));
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<AppointmentDTO>> obtenerPorFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(appointmentUseCase.obtenerCitasPorFecha(fecha));
    }

    @GetMapping("/estado/{status}")
    public ResponseEntity<List<AppointmentDTO>> obtenerPorEstado(@PathVariable String status) {
        return ResponseEntity.ok(appointmentUseCase.obtenerCitasPorEstado(status));
    }

    @GetMapping("/barbero/{barberId}/fecha/{fecha}")
    public ResponseEntity<List<AppointmentDTO>> obtenerPorBarberoYFecha(
            @PathVariable Long barberId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(appointmentUseCase.obtenerCitasPorBarberoYFecha(barberId, fecha));
    }
}
