package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.ServicioDTO;
import efinomina.message.efinomina.aplication.usescase.ServicioUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioUseCase servicioUseCase;

    public ServicioController(ServicioUseCase servicioUseCase) {
        this.servicioUseCase = servicioUseCase;
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> crear(@RequestBody ServicioDTO dto) {
        return new ResponseEntity<>(servicioUseCase.crearServicio(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> obtenerPorId(@PathVariable Long id) {
        Optional<ServicioDTO> result = servicioUseCase.obtenerServicioPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ServicioDTO>> obtenerTodos() {
        return ResponseEntity.ok(servicioUseCase.obtenerTodosLosServicios());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTO> actualizar(@PathVariable Long id, @RequestBody ServicioDTO dto) {
        ServicioDTO actualizado = servicioUseCase.actualizarServicio(id, dto);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioUseCase.eliminarServicio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ServicioDTO>> obtenerActivos() {
        return ResponseEntity.ok(servicioUseCase.obtenerServiciosActivos());
    }
}
