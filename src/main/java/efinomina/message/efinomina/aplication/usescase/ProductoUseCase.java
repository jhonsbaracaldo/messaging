package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.ProductoDTO;
import efinomina.message.efinomina.domain.model.entity.Producto;
import efinomina.message.efinomina.infraestructure.mapper.ProductoMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryProducto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoUseCase {
    private final RepositoryProducto repositoryProducto;

    public ProductoUseCase(RepositoryProducto repositoryProducto) {
        this.repositoryProducto = repositoryProducto;
    }

    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setCodigo(productoDTO.getCodigo());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setUnidadMedidaId(productoDTO.getUnidadMedidaId());
        producto.setStockMinimo(productoDTO.getStockMinimo());
        producto.setStockMaximo(productoDTO.getStockMaximo());
        producto.setEstado(true);
        producto.setCreatedAt(LocalDateTime.now());
        producto.setUpdatedAt(LocalDateTime.now());

        efinomina.message.efinomina.infraestructure.persistence.entity.Producto productoEntity =
            ProductoMapper.toEntity(producto);
        efinomina.message.efinomina.infraestructure.persistence.entity.Producto productoSaved =
            repositoryProducto.save(productoEntity);
        return ProductoMapper.toDTO(ProductoMapper.toDomain(productoSaved));
    }

    public Optional<ProductoDTO> obtenerProductoPorId(Integer id) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Producto> producto =
            repositoryProducto.findById(id);
        return producto.map(prod -> ProductoMapper.toDTO(ProductoMapper.toDomain(prod)));
    }

    public List<ProductoDTO> obtenerTodosLosProductos() {
        return repositoryProducto.findAll()
                .stream()
                .map(prod -> ProductoMapper.toDTO(ProductoMapper.toDomain(prod)))
                .collect(Collectors.toList());
    }

    public ProductoDTO actualizarProducto(Integer id, ProductoDTO productoDTO) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Producto> productoExistente =
            repositoryProducto.findById(id);

        if (productoExistente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Producto producto = productoExistente.get();
            producto.setCodigo(productoDTO.getCodigo());
            producto.setNombre(productoDTO.getNombre());
            producto.setDescripcion(productoDTO.getDescripcion());
            producto.setStockMinimo(productoDTO.getStockMinimo());
            producto.setStockMaximo(productoDTO.getStockMaximo());
            producto.setUpdatedAt(LocalDateTime.now());

            efinomina.message.efinomina.infraestructure.persistence.entity.Producto productoActualizado =
                repositoryProducto.save(producto);
            return ProductoMapper.toDTO(ProductoMapper.toDomain(productoActualizado));
        }
        return null;
    }

    public void eliminarProducto(Integer id) {
        repositoryProducto.deleteById(id);
    }
}

