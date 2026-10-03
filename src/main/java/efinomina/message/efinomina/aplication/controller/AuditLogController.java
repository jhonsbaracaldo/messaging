package efinomina.message.efinomina.aplication.controller;

import efinomina.message.efinomina.aplication.dto.AuditLogDTO;
import efinomina.message.efinomina.aplication.usescase.AuditLogUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auditoria")
public class AuditLogController {

    private final AuditLogUseCase auditLogUseCase;

    public AuditLogController(AuditLogUseCase auditLogUseCase) {
        this.auditLogUseCase = auditLogUseCase;
    }

    @PostMapping
    public ResponseEntity<AuditLogDTO> registrar(@RequestBody AuditLogDTO dto) {
        return new ResponseEntity<>(auditLogUseCase.registrarAuditoria(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLogDTO> obtenerPorId(@PathVariable Long id) {
        Optional<AuditLogDTO> result = auditLogUseCase.obtenerAuditoriaPorId(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AuditLogDTO>> obtenerTodas() {
        return ResponseEntity.ok(auditLogUseCase.obtenerTodasLasAuditorias());
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<AuditLogDTO>> obtenerPorUsuario(@PathVariable Long userId) {
        return ResponseEntity.ok(auditLogUseCase.obtenerAuditoriasPorUsuario(userId));
    }

    @GetMapping("/tabla/{tableName}")
    public ResponseEntity<List<AuditLogDTO>> obtenerPorTabla(@PathVariable String tableName) {
        return ResponseEntity.ok(auditLogUseCase.obtenerAuditoriasPorTabla(tableName));
    }

    @GetMapping("/modulo/{module}")
    public ResponseEntity<List<AuditLogDTO>> obtenerPorModulo(@PathVariable String module) {
        return ResponseEntity.ok(auditLogUseCase.obtenerAuditoriasPorModulo(module));
    }
}
