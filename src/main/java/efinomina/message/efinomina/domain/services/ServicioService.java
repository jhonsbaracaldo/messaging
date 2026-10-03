package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.Servicio;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    private final RepositoryServicio repository;

    public ServicioService(RepositoryServicio repository) {
        this.repository = repository;
    }

    public Servicio save(Servicio servicio) {
        servicio.setActive(true);
        servicio.setCreatedAt(LocalDateTime.now());
        return repository.save(servicio);
    }

    public Optional<Servicio> findById(Long id) {
        return repository.findById(id);
    }

    public List<Servicio> findAll() {
        return repository.findAll();
    }

    public List<Servicio> findAllActive() {
        return repository.findByActiveTrue();
    }

    public Servicio update(Long id, Servicio servicio) {
        return repository.findById(id).map(existing -> {
            existing.setName(servicio.getName());
            existing.setDescription(servicio.getDescription());
            existing.setPrice(servicio.getPrice());
            existing.setDurationMinutes(servicio.getDurationMinutes());
            existing.setActive(servicio.getActive());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Servicio no encontrado: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
