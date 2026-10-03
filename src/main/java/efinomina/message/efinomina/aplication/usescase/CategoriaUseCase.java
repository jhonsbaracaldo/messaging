package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.CategoriaDTO;
import efinomina.message.efinomina.domain.model.entity.Categoria;
import efinomina.message.efinomina.infraestructure.mapper.CategoriaMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryCategoria;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaUseCase {
    private final RepositoryCategoria repositoryCategoria;

    public CategoriaUseCase(RepositoryCategoria repositoryCategoria) {
        this.repositoryCategoria = repositoryCategoria;
    }

    public CategoriaDTO crearCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        categoria.setCreatedAt(LocalDateTime.now());
        categoria.setUpdatedAt(LocalDateTime.now());

        efinomina.message.efinomina.infraestructure.persistence.entity.Categoria categoriaEntity = CategoriaMapper.toEntity(categoria);
        efinomina.message.efinomina.infraestructure.persistence.entity.Categoria categoriaSaved = repositoryCategoria.save(categoriaEntity);
        return CategoriaMapper.toDTO(CategoriaMapper.toDomain(categoriaSaved));
    }

    public Optional<CategoriaDTO> obtenerCategoriaPorId(Integer id) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Categoria> categoria =
            repositoryCategoria.findById(id);
        return categoria.map(cat -> CategoriaMapper.toDTO(CategoriaMapper.toDomain(cat)));
    }

    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        return repositoryCategoria.findAll()
                .stream()
                .map(cat -> CategoriaMapper.toDTO(CategoriaMapper.toDomain(cat)))
                .collect(Collectors.toList());
    }

    public CategoriaDTO actualizarCategoria(Integer id, CategoriaDTO categoriaDTO) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Categoria> categoriaExistente =
            repositoryCategoria.findById(id);

        if (categoriaExistente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Categoria categoria = categoriaExistente.get();
            categoria.setNombre(categoriaDTO.getNombre());
            categoria.setDescripcion(categoriaDTO.getDescripcion());
            categoria.setUpdatedAt(LocalDateTime.now());

            efinomina.message.efinomina.infraestructure.persistence.entity.Categoria categoriaActualizada =
                repositoryCategoria.save(categoria);
            return CategoriaMapper.toDTO(CategoriaMapper.toDomain(categoriaActualizada));
        }
        return null;
    }

    public void eliminarCategoria(Integer id) {
        repositoryCategoria.deleteById(id);
    }
}

