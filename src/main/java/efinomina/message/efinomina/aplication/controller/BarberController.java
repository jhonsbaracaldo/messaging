package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.BarberDTO;
import efinomina.message.efinomina.aplication.usescase.BarberUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/barberos")
public class BarberController {

    private final BarberUseCase barberUseCase;

    public BarberController(BarberUseCase barberUseCase) {
        this.barberUseCase = barberUseCase;
    }

    @PostMapping
    public ResponseEntity<BarberDTO> crear(@RequestBody BarberDTO barberDTO) {
        BarberDTO creado = barberUseCase.crearBarbero(barberDTO);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarberDTO> obtenerPorId(@PathVariable Long id) {
        Optional<BarberDTO> barbero = barberUseCase.obtenerBarberoPorId(id);
        return barbero.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BarberDTO>> obtenerTodos() {
        return ResponseEntity.ok(barberUseCase.obtenerTodosLosBarberos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarberDTO> actualizar(@PathVariable Long id, @RequestBody BarberDTO barberDTO) {
        BarberDTO actualizado = barberUseCase.actualizarBarbero(id, barberDTO);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        barberUseCase.eliminarBarbero(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activos")
    public ResponseEntity<List<BarberDTO>> obtenerActivos() {
        return ResponseEntity.ok(barberUseCase.obtenerBarberosActivos());
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<BarberDTO>> obtenerPorUsuario(@PathVariable Long userId) {
        return ResponseEntity.ok(barberUseCase.obtenerBarberosPorUsuario(userId));
    }


    @GetMapping ("/telefono/{phone}")
    public ResponseEntity<List<BarberDTO>> obtenerPorTelefono(@PathVariable String phone) {
        return ResponseEntity.ok(barberUseCase.obtenerBarberosPorTelefono(phone));
    }
}
