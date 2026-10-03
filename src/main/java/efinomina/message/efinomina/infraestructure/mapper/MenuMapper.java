package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.MenuDTO;
import efinomina.message.efinomina.domain.model.entity.Menu;

public interface MenuMapper {

    static Menu toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Menu e) {
        if (e == null) return null;
        return new Menu(e.getId(), e.getName(), e.getIcon(), e.getPath(), e.getOrderNumber(), e.getActive(), e.getCreatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Menu toEntity(Menu d) {
        if (d == null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Menu e = new efinomina.message.efinomina.infraestructure.persistence.entity.Menu();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setIcon(d.getIcon());
        e.setPath(d.getPath());
        e.setOrderNumber(d.getOrderNumber());
        e.setActive(d.getActive());
        e.setCreatedAt(d.getCreatedAt());
        return e;
    }

    static MenuDTO toDTO(Menu d) {
        if (d == null) return null;
        return new MenuDTO(d.getId(), d.getName(), d.getIcon(), d.getPath(), d.getOrderNumber(), d.getActive(), d.getCreatedAt());
    }

    static Menu toDomainFromDTO(MenuDTO dto) {
        if (dto == null) return null;
        return new Menu(dto.getId(), dto.getName(), dto.getIcon(), dto.getPath(), dto.getOrderNumber(), dto.getActive(), dto.getCreatedAt());
    }
}
