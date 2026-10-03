package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.CashMovementDTO;
import efinomina.message.efinomina.aplication.usescase.CashMovementUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/caja")
public class CashMovementController {

    private final CashMovementUseCase cashMovementUseCase;

    public CashMovementController(CashMovementUseCase cashMovementUseCase) {
        this.cashMovementUseCase = cashMovementUseCase;
    }

    @PostMapping
    public ResponseEntity<CashMovementDTO> crear(@RequestBody CashMovementDTO dto) {
        return new ResponseEntity<>(cashMovementUseCase.crearMovimiento(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashMovementDTO> obtenerPorId(@PathVariable Long id) {
        Optional<CashMovementDTO> result = cashMovementUseCase.obtenerMovimientoPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CashMovementDTO>> obtenerTodos() {
        return ResponseEntity.ok(cashMovementUseCase.obtenerTodosLosMovimientos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cashMovementUseCase.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo/{type}")
    public ResponseEntity<List<CashMovementDTO>> obtenerPorTipo(@PathVariable String type) {
        return ResponseEntity.ok(cashMovementUseCase.obtenerMovimientosPorTipo(type));
    }
}
