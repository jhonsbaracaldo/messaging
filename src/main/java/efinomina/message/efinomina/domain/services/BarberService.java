package efinomina.message.efinomina.domain.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.Barber;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryBarber;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BarberService {

    private final RepositoryBarber repository;

    public BarberService(RepositoryBarber repository) {
        this.repository = repository;
    }

    public Barber save(Barber barber) {
        barber.setCreatedAt(LocalDateTime.now());
        return repository.save(barber);
    }

    public Optional<Barber> findById(Long id) {
        return repository.findById(id);
    }

    public List<Barber> findAll() {
        return repository.findAll();
    }

    public List<Barber> findAllActive() {
        return repository.findByActiveTrue();
    }

    public Barber update(Long id, Barber barber) {
        return repository.findById(id).map(existing -> {
            existing.setSpecialty(barber.getSpecialty());
            existing.setExperienceYears(barber.getExperienceYears());
            existing.setDescription(barber.getDescription());
            existing.setPhotoUrl(barber.getPhotoUrl());
            existing.setActive(barber.getActive());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Barbero no encontrado: " + id));
    }



    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Barber> finByPhone (String Phone){
        return repository.findByPhone(Phone);
    }

}
