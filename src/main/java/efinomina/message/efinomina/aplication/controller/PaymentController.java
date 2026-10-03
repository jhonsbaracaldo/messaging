package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.PaymentDTO;
import efinomina.message.efinomina.aplication.usescase.PaymentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    public PaymentController(PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> crear(@RequestBody PaymentDTO dto) {
        return new ResponseEntity<>(paymentUseCase.crearPago(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> obtenerPorId(@PathVariable Long id) {
        Optional<PaymentDTO> result = paymentUseCase.obtenerPagoPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> obtenerTodos() {
        return ResponseEntity.ok(paymentUseCase.obtenerTodosLosPagos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> actualizar(@PathVariable Long id, @RequestBody PaymentDTO dto) {
        PaymentDTO actualizado = paymentUseCase.actualizarPago(id, dto);
        if (actualizado != null) return ResponseEntity.ok(actualizado);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        paymentUseCase.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cita/{appointmentId}")
    public ResponseEntity<PaymentDTO> obtenerPorCita(@PathVariable Long appointmentId) {
        Optional<PaymentDTO> result = paymentUseCase.obtenerPagoPorCita(appointmentId);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{status}")
    public ResponseEntity<List<PaymentDTO>> obtenerPorEstado(@PathVariable String status) {
        return ResponseEntity.ok(paymentUseCase.obtenerPagosPorEstado(status));
    }

    @GetMapping("/metodo/{method}")
    public ResponseEntity<List<PaymentDTO>> obtenerPorMetodo(@PathVariable String method) {
        return ResponseEntity.ok(paymentUseCase.obtenerPagosPorMetodo(method));
    }
}
