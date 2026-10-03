package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.SubmenuDTO;
import efinomina.message.efinomina.domain.model.entity.Submenu;

public interface SubmenuMapper {

    static Submenu toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Submenu e) {
        if (e == null) return null;
        Long menuId = e.getMenu() != null ? e.getMenu().getId() : null;
        return new Submenu(e.getId(), menuId, e.getName(), e.getIcon(), e.getPath(), e.getOrderNumber(), e.getActive(), e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Submenu toEntity(Submenu d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Submenu e = new efinomina.message.efinomina.infraestructure.persistence.entity.Submenu();
        e.setId(d.getId());
        if (d.getMenuId() != null) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Menu menu = new efinomina.message.efinomina.infraestructure.persistence.entity.Menu();
            menu.setId(d.getMenuId());
            e.setMenu(menu);
        }
        e.setName(d.getName());
        e.setIcon(d.getIcon());
        e.setPath(d.getPath());
        e.setOrderNumber(d.getOrderNumber());
        e.setActive(d.getActive());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    static SubmenuDTO toDTO(Submenu d) {
        if (d == null) return null;
        return new SubmenuDTO(d.getId(), d.getMenuId(), d.getName(), d.getIcon(), d.getPath(), d.getOrderNumber(), d.getActive(), d.getCreatedAt());
    }

    static Submenu toDomainFromDTO(SubmenuDTO dto) {
        if (dto == null) return null;
        return new Submenu(dto.getId(), dto.getMenuId(), dto.getName(), dto.getIcon(), dto.getPath(), dto.getOrderNumber(), dto.getActive(), dto.getCreatedAt());
    }
}
