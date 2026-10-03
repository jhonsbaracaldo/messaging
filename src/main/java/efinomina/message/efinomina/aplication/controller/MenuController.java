package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.MenuDTO;
import efinomina.message.efinomina.aplication.usescase.MenuUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuUseCase menuUseCase;

    public MenuController(MenuUseCase menuUseCase) {
        this.menuUseCase = menuUseCase;
    }

    @PostMapping
    public ResponseEntity<MenuDTO> crear(@RequestBody MenuDTO dto) {
        return new ResponseEntity<>(menuUseCase.crearMenu(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> obtenerPorId(@PathVariable Long id) {
        Optional<MenuDTO> result = menuUseCase.obtenerMenuPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MenuDTO>> obtenerTodos() {
        return ResponseEntity.ok(menuUseCase.obtenerTodosLosMenus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> actualizar(@PathVariable Long id, @RequestBody MenuDTO dto) {
        MenuDTO actualizado = menuUseCase.actualizarMenu(id, dto);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        menuUseCase.eliminarMenu(id);
        return ResponseEntity.noContent().build();
    }
}
