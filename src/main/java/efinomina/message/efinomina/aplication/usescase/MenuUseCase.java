package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.MenuDTO;
import efinomina.message.efinomina.domain.model.entity.Menu;
import efinomina.message.efinomina.infraestructure.mapper.MenuMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryMenu;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuUseCase {

    private final RepositoryMenu repositoryMenu;

    public MenuUseCase(RepositoryMenu repositoryMenu) {
        this.repositoryMenu = repositoryMenu;
    }

    public MenuDTO crearMenu(MenuDTO dto) {
        Menu m = new Menu();
        m.setName(dto.getName());
        m.setIcon(dto.getIcon());
        m.setPath(dto.getPath());
        m.setOrderNumber(dto.getOrderNumber());
        m.setActive(true);
        m.setCreatedAt(LocalDateTime.now());
        return MenuMapper.toDTO(MenuMapper.toDomain(repositoryMenu.save(MenuMapper.toEntity(m))));
    }

    public Optional<MenuDTO> obtenerMenuPorId(Long id) {
        return repositoryMenu.findById(id).map(e -> MenuMapper.toDTO(MenuMapper.toDomain(e)));
    }

    public List<MenuDTO> obtenerTodosLosMenus() {
        return repositoryMenu.findAll().stream()
                .map(e -> MenuMapper.toDTO(MenuMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public MenuDTO actualizarMenu(Long id, MenuDTO dto) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Menu> existente = repositoryMenu.findById(id);
        if (existente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Menu e = existente.get();
            e.setName(dto.getName());
            e.setIcon(dto.getIcon());
            e.setPath(dto.getPath());
            e.setOrderNumber(dto.getOrderNumber());
            if (dto.getActive() != null) e.setActive(dto.getActive());
            return MenuMapper.toDTO(MenuMapper.toDomain(repositoryMenu.save(e)));
        }
        return null;
    }

    public void eliminarMenu(Long id) {
        repositoryMenu.deleteById(id);
    }
}
