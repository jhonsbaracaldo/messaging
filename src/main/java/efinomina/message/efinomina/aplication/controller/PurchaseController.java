package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.PurchaseDTO;
import efinomina.message.efinomina.aplication.usescase.PurchaseUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compras")
public class PurchaseController {

    private final PurchaseUseCase purchaseUseCase;

    public PurchaseController(PurchaseUseCase purchaseUseCase) {
        this.purchaseUseCase = purchaseUseCase;
    }

    @PostMapping
    public ResponseEntity<PurchaseDTO> crear(@RequestBody PurchaseDTO dto) {
        return new ResponseEntity<>(purchaseUseCase.crearCompra(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> obtenerPorId(@PathVariable Long id) {
        Optional<PurchaseDTO> result = purchaseUseCase.obtenerCompraPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> obtenerTodas() {
        return ResponseEntity.ok(purchaseUseCase.obtenerTodasLasCompras());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseDTO> actualizar(@PathVariable Long id, @RequestBody PurchaseDTO dto) {
        PurchaseDTO actualizado = purchaseUseCase.actualizarCompra(id, dto);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        purchaseUseCase.eliminarCompra(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/proveedor/{supplierId}")
    public ResponseEntity<List<PurchaseDTO>> obtenerPorProveedor(@PathVariable Long supplierId) {
        return ResponseEntity.ok(purchaseUseCase.obtenerComprasPorProveedor(supplierId));
    }
}
