package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.BarberDTO;
import efinomina.message.efinomina.domain.exception.BadRequestException;
import efinomina.message.efinomina.domain.model.entity.Barber;
import efinomina.message.efinomina.infraestructure.mapper.BarberMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryBarber;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarberUseCase {

    private final RepositoryBarber repositoryBarber;
    private final RepositoryUser repositoryUser;

    public BarberUseCase(RepositoryBarber repositoryBarber, RepositoryUser repositoryUser) {
        this.repositoryBarber = repositoryBarber;
        this.repositoryUser = repositoryUser;
    }

    public BarberDTO crearBarbero(BarberDTO barberDTO) {
        efinomina.message.efinomina.infraestructure.persistence.entity.User user = repositoryUser
                .findById(barberDTO.getUserId())
                .orElseThrow(() -> new BadRequestException("El usuario " + barberDTO.getUserId() + " no existe"));

        efinomina.message.efinomina.infraestructure.persistence.entity.Barber entity =
                new efinomina.message.efinomina.infraestructure.persistence.entity.Barber();
        entity.setUser(user);
        entity.setSpecialty(barberDTO.getSpecialty());
        entity.setExperienceYears(barberDTO.getExperienceYears());
        entity.setDescription(barberDTO.getDescription());
        entity.setPhotoUrl(barberDTO.getPhotoUrl());
        entity.setPhone(barberDTO.getPhone());
        entity.setActive(true);
        entity.setCreatedAt(LocalDateTime.now());

        efinomina.message.efinomina.infraestructure.persistence.entity.Barber saved = repositoryBarber.save(entity);
        return BarberMapper.toDTO(BarberMapper.toDomain(saved));
    }

    public Optional<BarberDTO> obtenerBarberoPorId(Long id) {
        return repositoryBarber.findById(id)
                .map(e -> BarberMapper.toDTO(BarberMapper.toDomain(e)));
    }

    public List<BarberDTO> obtenerTodosLosBarberos() {
        return repositoryBarber.findAll()
                .stream()
                .map(e -> BarberMapper.toDTO(BarberMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public BarberDTO actualizarBarbero(Long id, BarberDTO barberDTO) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Barber> existente =
                repositoryBarber.findById(id);
        if (existente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Barber e = existente.get();
            e.setSpecialty(barberDTO.getSpecialty());
            e.setExperienceYears(barberDTO.getExperienceYears());
            e.setDescription(barberDTO.getDescription());
            e.setPhotoUrl(barberDTO.getPhotoUrl());
            e.setPhone(barberDTO.getPhone());
            if (barberDTO.getActive() != null) e.setActive(barberDTO.getActive());
            return BarberMapper.toDTO(BarberMapper.toDomain(repositoryBarber.save(e)));
        }
        return null;
    }

    public void eliminarBarbero(Long id) {
        repositoryBarber.deleteById(id);
    }

    public List<BarberDTO> obtenerBarberosActivos() {
        return repositoryBarber.findByActiveTrue()
                .stream()
                .map(e -> BarberMapper.toDTO(BarberMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<BarberDTO> obtenerBarberosPorUsuario(Long userId) {
        return repositoryBarber.findByUserId(userId)
                .stream()
                .map(e -> BarberMapper.toDTO(BarberMapper.toDomain(e)))
                .collect(Collectors.toList());
    }


    public List<BarberDTO> obtenerBarberosPorTelefono(String phone) {
        return repositoryBarber.findByPhone(phone)
                .stream()
                .map(e -> BarberMapper.toDTO(BarberMapper.toDomain(e)))
                .toList();
    }
}
