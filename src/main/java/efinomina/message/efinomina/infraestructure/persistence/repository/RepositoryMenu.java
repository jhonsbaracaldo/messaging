package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryMenu extends JpaRepository<Menu, Long> {
    List<Menu> findByActiveTrueOrderByOrderNumberAsc();
}
