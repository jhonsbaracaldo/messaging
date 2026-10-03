package efinomina.message.efinomina.infraestructure.mapper;

import efinomina.message.efinomina.aplication.dto.ProductoDTO;
import efinomina.message.efinomina.domain.model.entity.Producto;

public interface ProductoMapper {

    static Producto toDomain(efinomina.message.efinomina.infraestructure.persistence.entity.Producto e) {
        if (e==null) return null;
        return new Producto(e.getId(), e.getCodigo(), e.getNombre(), e.getDescripcion(), CategoriaMapper.toDomain(e.getCategoria()), e.getUnidadMedidaId(), e.getStockMinimo(), e.getStockMaximo(), e.getEstado(), e.getCreatedAt(), e.getUpdatedAt());
    }

    static efinomina.message.efinomina.infraestructure.persistence.entity.Producto toEntity(Producto d) {
        if (d==null) return null;
        efinomina.message.efinomina.infraestructure.persistence.entity.Producto e = new efinomina.message.efinomina.infraestructure.persistence.entity.Producto();
        e.setId(d.getId());
        e.setCodigo(d.getCodigo());
        e.setNombre(d.getNombre());
        e.setDescripcion(d.getDescripcion());
        e.setCategoria(CategoriaMapper.toEntity(d.getCategoria()));
        e.setUnidadMedidaId(d.getUnidadMedidaId());
        e.setStockMinimo(d.getStockMinimo());
        e.setStockMaximo(d.getStockMaximo());
        e.setEstado(d.getEstado());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }

    static ProductoDTO toDTO(Producto d) {
        if (d==null) return null;
        return new ProductoDTO(d.getId(), d.getCodigo(), d.getNombre(), d.getDescripcion(), CategoriaMapper.toDTO(d.getCategoria()), d.getUnidadMedidaId(), d.getStockMinimo(), d.getStockMaximo(), d.getEstado(), d.getCreatedAt(), d.getUpdatedAt());
    }

    static Producto toDomainFromDTO(ProductoDTO dto) {
        if (dto==null) return null;
        return new Producto(dto.getId(), dto.getCodigo(), dto.getNombre(), dto.getDescripcion(), CategoriaMapper.toDomainFromDTO(dto.getCategoria()), dto.getUnidadMedidaId(), dto.getStockMinimo(), dto.getStockMaximo(), dto.getEstado(), dto.getCreatedAt(), dto.getUpdatedAt());
    }
}
