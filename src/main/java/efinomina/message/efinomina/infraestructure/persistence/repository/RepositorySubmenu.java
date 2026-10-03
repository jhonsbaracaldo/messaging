package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.Submenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorySubmenu extends JpaRepository<Submenu, Long> {
    List<Submenu> findByMenuId(Long menuId);
    List<Submenu> findByMenuIdAndActiveTrueOrderByOrderNumberAsc(Long menuId);
}
