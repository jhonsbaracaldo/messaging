package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.SubmenuDTO;
import efinomina.message.efinomina.aplication.usescase.SubmenuUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/submenus")
public class SubmenuController {

    private final SubmenuUseCase submenuUseCase;

    public SubmenuController(SubmenuUseCase submenuUseCase) {
        this.submenuUseCase = submenuUseCase;
    }

    @PostMapping
    public ResponseEntity<SubmenuDTO> crear(@RequestBody SubmenuDTO dto) {
        return new ResponseEntity<>(submenuUseCase.crearSubmenu(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmenuDTO> obtenerPorId(@PathVariable Long id) {
        Optional<SubmenuDTO> result = submenuUseCase.obtenerSubmenuPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SubmenuDTO>> obtenerTodos() {
        return ResponseEntity.ok(submenuUseCase.obtenerTodosLosSubmenus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubmenuDTO> actualizar(@PathVariable Long id, @RequestBody SubmenuDTO dto) {
        SubmenuDTO actualizado = submenuUseCase.actualizarSubmenu(id, dto);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        submenuUseCase.eliminarSubmenu(id);
        return ResponseEntity.noContent().build();
    }
}
