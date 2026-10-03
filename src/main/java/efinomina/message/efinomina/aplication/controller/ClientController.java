package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.ClientDTO;
import efinomina.message.efinomina.aplication.usescase.ClientUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientUseCase clientUseCase;

    public ClientController(ClientUseCase clientUseCase) {
        this.clientUseCase = clientUseCase;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> crear(@RequestBody ClientDTO clientDTO) {
        ClientDTO creado = clientUseCase.crearCliente(clientDTO);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> obtenerPorId(@PathVariable Long id) {
        Optional<ClientDTO> cliente = clientUseCase.obtenerClientePorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> obtenerTodos() {
        return ResponseEntity.ok(clientUseCase.obtenerTodosLosClientes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> actualizar(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        ClientDTO actualizado = clientUseCase.actualizarCliente(id, clientDTO);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clientUseCase.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClientDTO> obtenerPorEmail(@PathVariable String email) {
        Optional<ClientDTO> cliente = clientUseCase.obtenerClientePorEmail(email);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/telefono/{phone}")
    public ResponseEntity<ClientDTO> obtenerPorTelefono(@PathVariable String phone) {
        Optional<ClientDTO> cliente = clientUseCase.obtenerClientePorTelefono(phone);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ClientDTO>> obtenerActivos() {
        return ResponseEntity.ok(clientUseCase.obtenerClientesActivos());
    }
}
