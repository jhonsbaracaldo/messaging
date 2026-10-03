package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.ProductCategoryDTO;
import efinomina.message.efinomina.aplication.usescase.ProductCategoryUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {

    private final ProductCategoryUseCase productCategoryUseCase;

    public ProductCategoryController(ProductCategoryUseCase productCategoryUseCase) {
        this.productCategoryUseCase = productCategoryUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductCategoryDTO> crear(@RequestBody ProductCategoryDTO dto) {
        return new ResponseEntity<>(productCategoryUseCase.crearCategoria(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> obtenerPorId(@PathVariable Long id) {
        Optional<ProductCategoryDTO> result = productCategoryUseCase.obtenerCategoriaPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDTO>> obtenerTodas() {
        return ResponseEntity.ok(productCategoryUseCase.obtenerTodasLasCategorias());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> actualizar(@PathVariable Long id, @RequestBody ProductCategoryDTO dto) {
        ProductCategoryDTO actualizado = productCategoryUseCase.actualizarCategoria(id, dto);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productCategoryUseCase.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/activas")
    public ResponseEntity<List<ProductCategoryDTO>> obtenerActivas() {
        return ResponseEntity.ok(productCategoryUseCase.obtenerCategoriasActivas());
    }
}
