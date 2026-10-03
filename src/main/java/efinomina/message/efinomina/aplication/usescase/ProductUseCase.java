package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.ProductDTO;
import efinomina.message.efinomina.domain.model.entity.Product;
import efinomina.message.efinomina.infraestructure.mapper.ProductMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryProduct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductUseCase {

    private final RepositoryProduct repositoryProduct;

    public ProductUseCase(RepositoryProduct repositoryProduct) {
        this.repositoryProduct = repositoryProduct;
    }

    public ProductDTO crearProducto(ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setBarcode(dto.getBarcode());
        p.setCategoryId(dto.getCategoryId());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock() != null ? dto.getStock() : 0);
        p.setMinimumStock(dto.getMinimumStock());
        p.setActive(true);
        p.setCreatedAt(LocalDateTime.now());
        p.setUpdatedAt(LocalDateTime.now());
        return ProductMapper.toDTO(ProductMapper.toDomain(repositoryProduct.save(ProductMapper.toEntity(p))));
    }

    public Optional<ProductDTO> obtenerProductoPorId(Long id) {
        return repositoryProduct.findById(id).map(e -> ProductMapper.toDTO(ProductMapper.toDomain(e)));
    }

    public List<ProductDTO> obtenerTodosLosProductos() {
        return repositoryProduct.findAll().stream()
                .map(e -> ProductMapper.toDTO(ProductMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public ProductDTO actualizarProducto(Long id, ProductDTO dto) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Product> existente = repositoryProduct.findById(id);
        if (existente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Product e = existente.get();
            e.setName(dto.getName());
            e.setDescription(dto.getDescription());
            e.setBarcode(dto.getBarcode());
            e.setPrice(dto.getPrice());
            e.setStock(dto.getStock());
            e.setMinimumStock(dto.getMinimumStock());
            if (dto.getActive() != null) e.setActive(dto.getActive());
            e.setUpdatedAt(LocalDateTime.now());
            return ProductMapper.toDTO(ProductMapper.toDomain(repositoryProduct.save(e)));
        }
        return null;
    }

    public void eliminarProducto(Long id) {
        repositoryProduct.deleteById(id);
    }

    public Optional<ProductDTO> obtenerProductoPorBarcode(String barcode) {
        return repositoryProduct.findByBarcode(barcode).map(e -> ProductMapper.toDTO(ProductMapper.toDomain(e)));
    }

    public List<ProductDTO> obtenerProductosPorCategoria(Long categoryId) {
        return repositoryProduct.findByCategoryId(categoryId).stream()
                .map(e -> ProductMapper.toDTO(ProductMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> obtenerProductosActivos() {
        return repositoryProduct.findByActiveTrue().stream()
                .map(e -> ProductMapper.toDTO(ProductMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> obtenerProductosBajoStock(Integer minimumStock) {
        return repositoryProduct.findByStockLessThanEqual(minimumStock).stream()
                .map(e -> ProductMapper.toDTO(ProductMapper.toDomain(e)))
                .collect(Collectors.toList());
    }
}
