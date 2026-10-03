package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.SupplierDTO;
import efinomina.message.efinomina.aplication.usescase.SupplierUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedores")
public class SupplierController {

    private final SupplierUseCase supplierUseCase;

    public SupplierController(SupplierUseCase supplierUseCase) {
        this.supplierUseCase = supplierUseCase;
    }

    @PostMapping
    public ResponseEntity<SupplierDTO> crear(@RequestBody SupplierDTO dto) {
        return new ResponseEntity<>(supplierUseCase.crearProveedor(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> obtenerPorId(@PathVariable Long id) {
        Optional<SupplierDTO> result = supplierUseCase.obtenerProveedorPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SupplierDTO>> obtenerTodos() {
        return ResponseEntity.ok(supplierUseCase.obtenerTodosLosProveedores());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> actualizar(@PathVariable Long id, @RequestBody SupplierDTO dto) {
        SupplierDTO actualizado = supplierUseCase.actualizarProveedor(id, dto);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        supplierUseCase.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
