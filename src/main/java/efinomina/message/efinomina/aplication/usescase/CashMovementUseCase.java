package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.CashMovementDTO;
import efinomina.message.efinomina.domain.model.entity.CashMovement;
import efinomina.message.efinomina.infraestructure.mapper.CashMovementMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryCashMovement;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CashMovementUseCase {

    private final RepositoryCashMovement repositoryCashMovement;

    public CashMovementUseCase(RepositoryCashMovement repositoryCashMovement) {
        this.repositoryCashMovement = repositoryCashMovement;
    }

    public CashMovementDTO crearMovimiento(CashMovementDTO dto) {
        CashMovement cm = new CashMovement();
        cm.setType(dto.getType());
        cm.setAmount(dto.getAmount());
        cm.setDescription(dto.getDescription());
        cm.setCreatedById(dto.getCreatedById());
        cm.setCreatedAt(LocalDateTime.now());
        return CashMovementMapper.toDTO(CashMovementMapper.toDomain(repositoryCashMovement.save(CashMovementMapper.toEntity(cm))));
    }

    public Optional<CashMovementDTO> obtenerMovimientoPorId(Long id) {
        return repositoryCashMovement.findById(id).map(e -> CashMovementMapper.toDTO(CashMovementMapper.toDomain(e)));
    }

    public List<CashMovementDTO> obtenerTodosLosMovimientos() {
        return repositoryCashMovement.findAll().stream()
                .map(e -> CashMovementMapper.toDTO(CashMovementMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public void eliminarMovimiento(Long id) {
        repositoryCashMovement.deleteById(id);
    }

    public List<CashMovementDTO> obtenerMovimientosPorTipo(String type) {
        return repositoryCashMovement.findByType(type).stream()
                .map(e -> CashMovementMapper.toDTO(CashMovementMapper.toDomain(e)))
                .collect(Collectors.toList());
    }
}
