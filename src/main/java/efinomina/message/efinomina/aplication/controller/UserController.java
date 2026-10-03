package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.UserDTO;
import efinomina.message.efinomina.aplication.usescase.UserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public ResponseEntity<UserDTO> crear(@RequestBody UserDTO userDTO) {
        UserDTO usuarioCreado = userUseCase.crearUsuario(userDTO);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> obtenerPorId(@PathVariable Long id) {
        Optional<UserDTO> usuario = userUseCase.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> obtenerTodos() {
        List<UserDTO> usuarios = userUseCase.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> actualizar(@PathVariable Long id,
                                               @RequestBody UserDTO userDTO) {
        UserDTO usuarioActualizado = userUseCase.actualizarUsuario(id, userDTO);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        userUseCase.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
