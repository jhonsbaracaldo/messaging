package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.StockMovementDTO;
import efinomina.message.efinomina.aplication.usescase.StockMovementUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    private final StockMovementUseCase stockMovementUseCase;

    public StockMovementController(StockMovementUseCase stockMovementUseCase) {
        this.stockMovementUseCase = stockMovementUseCase;
    }

    @PostMapping
    public ResponseEntity<StockMovementDTO> crear(@RequestBody StockMovementDTO dto) {
        return new ResponseEntity<>(stockMovementUseCase.crearMovimiento(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovementDTO> obtenerPorId(@PathVariable Long id) {
        Optional<StockMovementDTO> result = stockMovementUseCase.obtenerMovimientoPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StockMovementDTO>> obtenerTodos() {
        return ResponseEntity.ok(stockMovementUseCase.obtenerTodosLosMovimientos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        stockMovementUseCase.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/producto/{productId}")
    public ResponseEntity<List<StockMovementDTO>> obtenerPorProducto(@PathVariable Long productId) {
        return ResponseEntity.ok(stockMovementUseCase.obtenerMovimientosPorProducto(productId));
    }

    @GetMapping("/tipo/{movementType}")
    public ResponseEntity<List<StockMovementDTO>> obtenerPorTipo(@PathVariable String movementType) {
        return ResponseEntity.ok(stockMovementUseCase.obtenerMovimientosPorTipo(movementType));
    }
}
