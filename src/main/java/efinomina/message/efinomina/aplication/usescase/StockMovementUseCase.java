package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.StockMovementDTO;
import efinomina.message.efinomina.domain.model.entity.StockMovement;
import efinomina.message.efinomina.infraestructure.mapper.StockMovementMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryStockMovement;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockMovementUseCase {

    private final RepositoryStockMovement repositoryStockMovement;

    public StockMovementUseCase(RepositoryStockMovement repositoryStockMovement) {
        this.repositoryStockMovement = repositoryStockMovement;
    }

    public StockMovementDTO crearMovimiento(StockMovementDTO dto) {
        StockMovement sm = new StockMovement();
        sm.setProductId(dto.getProductId());
        sm.setMovementType(dto.getMovementType());
        sm.setQuantity(dto.getQuantity());
        sm.setPreviousStock(dto.getPreviousStock());
        sm.setNewStock(dto.getNewStock());
        sm.setReason(dto.getReason());
        sm.setCreatedById(dto.getCreatedById());
        sm.setCreatedAt(LocalDateTime.now());
        return StockMovementMapper.toDTO(StockMovementMapper.toDomain(repositoryStockMovement.save(StockMovementMapper.toEntity(sm))));
    }

    public Optional<StockMovementDTO> obtenerMovimientoPorId(Long id) {
        return repositoryStockMovement.findById(id).map(e -> StockMovementMapper.toDTO(StockMovementMapper.toDomain(e)));
    }

    public List<StockMovementDTO> obtenerTodosLosMovimientos() {
        return repositoryStockMovement.findAll().stream()
                .map(e -> StockMovementMapper.toDTO(StockMovementMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public void eliminarMovimiento(Long id) {
        repositoryStockMovement.deleteById(id);
    }

    public List<StockMovementDTO> obtenerMovimientosPorProducto(Long productId) {
        return repositoryStockMovement.findByProductId(productId).stream()
                .map(e -> StockMovementMapper.toDTO(StockMovementMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<StockMovementDTO> obtenerMovimientosPorTipo(String movementType) {
        return repositoryStockMovement.findByMovementType(movementType).stream()
                .map(e -> StockMovementMapper.toDTO(StockMovementMapper.toDomain(e)))
                .collect(Collectors.toList());
    }
}
