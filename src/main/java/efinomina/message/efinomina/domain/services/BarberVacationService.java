package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.BarberVacation;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryBarberVacation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BarberVacationService {

    private final RepositoryBarberVacation repository;

    public BarberVacationService(RepositoryBarberVacation repository) {
        this.repository = repository;
    }

    public BarberVacation save(BarberVacation vacation) {
        vacation.setCreatedAt(LocalDateTime.now());
        return repository.save(vacation);
    }

    public Optional<BarberVacation> findById(Long id) {
        return repository.findById(id);
    }

    public List<BarberVacation> findByBarber(Long barberId) {
        return repository.findByBarberId(barberId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
