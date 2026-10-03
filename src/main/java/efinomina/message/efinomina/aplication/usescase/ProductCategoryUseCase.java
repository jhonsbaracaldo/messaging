package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.ProductCategoryDTO;
import efinomina.message.efinomina.domain.model.entity.ProductCategory;
import efinomina.message.efinomina.infraestructure.mapper.ProductCategoryMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryProductCategory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCategoryUseCase {

    private final RepositoryProductCategory repositoryProductCategory;

    public ProductCategoryUseCase(RepositoryProductCategory repositoryProductCategory) {
        this.repositoryProductCategory = repositoryProductCategory;
    }

    public ProductCategoryDTO crearCategoria(ProductCategoryDTO dto) {
        ProductCategory pc = new ProductCategory();
        pc.setName(dto.getName());
        pc.setDescription(dto.getDescription());
        pc.setActive(true);
        pc.setCreatedAt(LocalDateTime.now());
        return ProductCategoryMapper.toDTO(ProductCategoryMapper.toDomain(repositoryProductCategory.save(ProductCategoryMapper.toEntity(pc))));
    }

    public Optional<ProductCategoryDTO> obtenerCategoriaPorId(Long id) {
        return repositoryProductCategory.findById(id).map(e -> ProductCategoryMapper.toDTO(ProductCategoryMapper.toDomain(e)));
    }

    public List<ProductCategoryDTO> obtenerTodasLasCategorias() {
        return repositoryProductCategory.findAll().stream()
                .map(e -> ProductCategoryMapper.toDTO(ProductCategoryMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public ProductCategoryDTO actualizarCategoria(Long id, ProductCategoryDTO dto) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.ProductCategory> existente = repositoryProductCategory.findById(id);
        if (existente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.ProductCategory e = existente.get();
            e.setName(dto.getName());
            e.setDescription(dto.getDescription());
            if (dto.getActive() != null) e.setActive(dto.getActive());
            return ProductCategoryMapper.toDTO(ProductCategoryMapper.toDomain(repositoryProductCategory.save(e)));
        }
        return null;
    }

    public void eliminarCategoria(Long id) {
        repositoryProductCategory.deleteById(id);
    }

    public List<ProductCategoryDTO> obtenerCategoriasActivas() {
        return repositoryProductCategory.findByActiveTrue().stream()
                .map(e -> ProductCategoryMapper.toDTO(ProductCategoryMapper.toDomain(e)))
                .collect(Collectors.toList());
    }
}
