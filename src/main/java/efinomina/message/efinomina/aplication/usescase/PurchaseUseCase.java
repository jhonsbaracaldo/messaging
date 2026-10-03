package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.PurchaseDTO;
import efinomina.message.efinomina.domain.model.entity.Purchase;
import efinomina.message.efinomina.infraestructure.mapper.PurchaseMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryPurchase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseUseCase {

    private final RepositoryPurchase repositoryPurchase;

    public PurchaseUseCase(RepositoryPurchase repositoryPurchase) {
        this.repositoryPurchase = repositoryPurchase;
    }

    public PurchaseDTO crearCompra(PurchaseDTO dto) {
        Purchase p = new Purchase();
        p.setSupplierId(dto.getSupplierId());
        p.setTotal(dto.getTotal());
        p.setInvoiceNumber(dto.getInvoiceNumber());
        p.setCreatedById(dto.getCreatedById());
        p.setCreatedAt(LocalDateTime.now());
        return PurchaseMapper.toDTO(PurchaseMapper.toDomain(repositoryPurchase.save(PurchaseMapper.toEntity(p))));
    }

    public Optional<PurchaseDTO> obtenerCompraPorId(Long id) {
        return repositoryPurchase.findById(id).map(e -> PurchaseMapper.toDTO(PurchaseMapper.toDomain(e)));
    }

    public List<PurchaseDTO> obtenerTodasLasCompras() {
        return repositoryPurchase.findAll().stream()
                .map(e -> PurchaseMapper.toDTO(PurchaseMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public PurchaseDTO actualizarCompra(Long id, PurchaseDTO dto) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Purchase> existente = repositoryPurchase.findById(id);
        if (existente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Purchase e = existente.get();
            e.setTotal(dto.getTotal());
            e.setInvoiceNumber(dto.getInvoiceNumber());
            return PurchaseMapper.toDTO(PurchaseMapper.toDomain(repositoryPurchase.save(e)));
        }
        return null;
    }

    public void eliminarCompra(Long id) {
        repositoryPurchase.deleteById(id);
    }

    public List<PurchaseDTO> obtenerComprasPorProveedor(Long supplierId) {
        return repositoryPurchase.findBySupplierId(supplierId).stream()
                .map(e -> PurchaseMapper.toDTO(PurchaseMapper.toDomain(e)))
                .collect(Collectors.toList());
    }
}
