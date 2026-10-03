package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.Menu;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryMenu;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final RepositoryMenu repository;

    public MenuService(RepositoryMenu repository) {
        this.repository = repository;
    }

    public Menu save(Menu menu) {
        menu.setActive(true);
        menu.setCreatedAt(LocalDateTime.now());
        return repository.save(menu);
    }

    public Optional<Menu> findById(Long id) {
        return repository.findById(id);
    }

    public List<Menu> findAll() {
        return repository.findAll();
    }

    public List<Menu> findAllActive() {
        return repository.findByActiveTrueOrderByOrderNumberAsc();
    }

    public Menu update(Long id, Menu menu) {
        return repository.findById(id).map(existing -> {
            existing.setName(menu.getName());
            existing.setIcon(menu.getIcon());
            existing.setPath(menu.getPath());
            existing.setOrderNumber(menu.getOrderNumber());
            existing.setActive(menu.getActive());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Menú no encontrado: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
