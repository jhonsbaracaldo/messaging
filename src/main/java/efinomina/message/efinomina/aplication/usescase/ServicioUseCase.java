package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.ServicioDTO;
import efinomina.message.efinomina.domain.model.entity.Servicio;
import efinomina.message.efinomina.infraestructure.mapper.ServicioMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioUseCase {

    private final RepositoryServicio repositoryServicio;

    public ServicioUseCase(RepositoryServicio repositoryServicio) {
        this.repositoryServicio = repositoryServicio;
    }

    public ServicioDTO crearServicio(ServicioDTO dto) {
        Servicio s = new Servicio();
        s.setName(dto.getName());
        s.setDescription(dto.getDescription());
        s.setPrice(dto.getPrice());
        s.setDurationMinutes(dto.getDurationMinutes());
        s.setActive(true);
        s.setCreatedAt(LocalDateTime.now());
        return ServicioMapper.toDTO(ServicioMapper.toDomain(repositoryServicio.save(ServicioMapper.toEntity(s))));
    }

    public Optional<ServicioDTO> obtenerServicioPorId(Long id) {
        return repositoryServicio.findById(id).map(e -> ServicioMapper.toDTO(ServicioMapper.toDomain(e)));
    }

    public List<ServicioDTO> obtenerTodosLosServicios() {
        return repositoryServicio.findAll().stream()
                .map(e -> ServicioMapper.toDTO(ServicioMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public ServicioDTO actualizarServicio(Long id, ServicioDTO dto) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Servicio> existente = repositoryServicio.findById(id);
        if (existente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Servicio e = existente.get();
            e.setName(dto.getName());
            e.setDescription(dto.getDescription());
            e.setPrice(dto.getPrice());
            e.setDurationMinutes(dto.getDurationMinutes());
            if (dto.getActive() != null) e.setActive(dto.getActive());
            return ServicioMapper.toDTO(ServicioMapper.toDomain(repositoryServicio.save(e)));
        }
        return null;
    }

    public void eliminarServicio(Long id) {
        repositoryServicio.deleteById(id);
    }

    public List<ServicioDTO> obtenerServiciosActivos() {
        return repositoryServicio.findByActiveTrue().stream()
                .map(e -> ServicioMapper.toDTO(ServicioMapper.toDomain(e)))
                .collect(Collectors.toList());
    }
}
