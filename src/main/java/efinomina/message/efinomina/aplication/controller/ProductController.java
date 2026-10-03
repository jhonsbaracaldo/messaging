package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.ProductDTO;
import efinomina.message.efinomina.aplication.usescase.ProductUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> crear(@RequestBody ProductDTO dto) {
        return new ResponseEntity<>(productUseCase.crearProducto(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> obtenerPorId(@PathVariable Long id) {
        Optional<ProductDTO> result = productUseCase.obtenerProductoPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> obtenerTodos() {
        return ResponseEntity.ok(productUseCase.obtenerTodosLosProductos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> actualizar(@PathVariable Long id, @RequestBody ProductDTO dto) {
        ProductDTO actualizado = productUseCase.actualizarProducto(id, dto);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productUseCase.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<ProductDTO> obtenerPorBarcode(@PathVariable String barcode) {
        Optional<ProductDTO> result = productUseCase.obtenerProductoPorBarcode(barcode);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoryId}")
    public ResponseEntity<List<ProductDTO>> obtenerPorCategoria(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productUseCase.obtenerProductosPorCategoria(categoryId));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ProductDTO>> obtenerActivos() {
        return ResponseEntity.ok(productUseCase.obtenerProductosActivos());
    }

    @GetMapping("/bajo-stock/{minimumStock}")
    public ResponseEntity<List<ProductDTO>> obtenerBajoStock(@PathVariable Integer minimumStock) {
        return ResponseEntity.ok(productUseCase.obtenerProductosBajoStock(minimumStock));
    }
}
