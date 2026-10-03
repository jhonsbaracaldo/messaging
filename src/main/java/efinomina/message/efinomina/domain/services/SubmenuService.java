package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.Submenu;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositorySubmenu;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SubmenuService {

    private final RepositorySubmenu repository;

    public SubmenuService(RepositorySubmenu repository) {
        this.repository = repository;
    }

    public Submenu save(Submenu submenu) {
        submenu.setActive(true);
        submenu.setCreatedAt(LocalDateTime.now());
        return repository.save(submenu);
    }

    public Optional<Submenu> findById(Long id) {
        return repository.findById(id);
    }

    public List<Submenu> findByMenu(Long menuId) {
        return repository.findByMenuId(menuId);
    }

    public List<Submenu> findActiveByMenu(Long menuId) {
        return repository.findByMenuIdAndActiveTrueOrderByOrderNumberAsc(menuId);
    }

    public Submenu update(Long id, Submenu submenu) {
        return repository.findById(id).map(existing -> {
            existing.setName(submenu.getName());
            existing.setIcon(submenu.getIcon());
            existing.setPath(submenu.getPath());
            existing.setOrderNumber(submenu.getOrderNumber());
            existing.setActive(submenu.getActive());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Submenú no encontrado: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
