package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.SubmenuDTO;
import efinomina.message.efinomina.domain.model.entity.Submenu;
import efinomina.message.efinomina.infraestructure.mapper.SubmenuMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositorySubmenu;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubmenuUseCase {

    private final RepositorySubmenu repositorySubmenu;

    public SubmenuUseCase(RepositorySubmenu repositorySubmenu) {
        this.repositorySubmenu = repositorySubmenu;
    }

    public SubmenuDTO crearSubmenu(SubmenuDTO dto) {
        Submenu s = new Submenu();
        s.setMenuId(dto.getMenuId());
        s.setName(dto.getName());
        s.setIcon(dto.getIcon());
        s.setPath(dto.getPath());
        s.setOrderNumber(dto.getOrderNumber());
        s.setActive(true);
        s.setCreatedAt(LocalDateTime.now());
        return SubmenuMapper.toDTO(SubmenuMapper.toDomain(repositorySubmenu.save(SubmenuMapper.toEntity(s))));
    }

    public Optional<SubmenuDTO> obtenerSubmenuPorId(Long id) {
        return repositorySubmenu.findById(id).map(e -> SubmenuMapper.toDTO(SubmenuMapper.toDomain(e)));
    }

    public List<SubmenuDTO> obtenerTodosLosSubmenus() {
        return repositorySubmenu.findAll().stream()
                .map(e -> SubmenuMapper.toDTO(SubmenuMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public SubmenuDTO actualizarSubmenu(Long id, SubmenuDTO dto) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Submenu> existente = repositorySubmenu.findById(id);
        if (existente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Submenu e = existente.get();
            e.setName(dto.getName());
            e.setIcon(dto.getIcon());
            e.setPath(dto.getPath());
            e.setOrderNumber(dto.getOrderNumber());
            if (dto.getActive() != null) e.setActive(dto.getActive());
            return SubmenuMapper.toDTO(SubmenuMapper.toDomain(repositorySubmenu.save(e)));
        }
        return null;
    }

    public void eliminarSubmenu(Long id) {
        repositorySubmenu.deleteById(id);
    }
}
