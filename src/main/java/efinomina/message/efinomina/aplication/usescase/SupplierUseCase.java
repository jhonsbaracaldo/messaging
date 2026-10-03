package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.SupplierDTO;
import efinomina.message.efinomina.domain.model.entity.Supplier;
import efinomina.message.efinomina.infraestructure.mapper.SupplierMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositorySupplier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierUseCase {

    private final RepositorySupplier repositorySupplier;

    public SupplierUseCase(RepositorySupplier repositorySupplier) {
        this.repositorySupplier = repositorySupplier;
    }

    public SupplierDTO crearProveedor(SupplierDTO dto) {
        Supplier s = new Supplier();
        s.setName(dto.getName());
        s.setPhone(dto.getPhone());
        s.setEmail(dto.getEmail());
        s.setCompany(dto.getCompany());
        s.setAddress(dto.getAddress());
        s.setActive(true);
        s.setCreatedAt(LocalDateTime.now());
        return SupplierMapper.toDTO(SupplierMapper.toDomain(repositorySupplier.save(SupplierMapper.toEntity(s))));
    }

    public Optional<SupplierDTO> obtenerProveedorPorId(Long id) {
        return repositorySupplier.findById(id).map(e -> SupplierMapper.toDTO(SupplierMapper.toDomain(e)));
    }

    public List<SupplierDTO> obtenerTodosLosProveedores() {
        return repositorySupplier.findAll().stream()
                .map(e -> SupplierMapper.toDTO(SupplierMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public SupplierDTO actualizarProveedor(Long id, SupplierDTO dto) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Supplier> existente = repositorySupplier.findById(id);
        if (existente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Supplier e = existente.get();
            e.setName(dto.getName());
            e.setPhone(dto.getPhone());
            e.setEmail(dto.getEmail());
            e.setCompany(dto.getCompany());
            e.setAddress(dto.getAddress());
            if (dto.getActive() != null) e.setActive(dto.getActive());
            return SupplierMapper.toDTO(SupplierMapper.toDomain(repositorySupplier.save(e)));
        }
        return null;
    }

    public void eliminarProveedor(Long id) {
        repositorySupplier.deleteById(id);
    }
}
