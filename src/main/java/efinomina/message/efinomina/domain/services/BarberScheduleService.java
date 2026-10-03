package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.BarberSchedule;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryBarberSchedule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberScheduleService {

    private final RepositoryBarberSchedule repository;

    public BarberScheduleService(RepositoryBarberSchedule repository) {
        this.repository = repository;
    }

    public BarberSchedule save(BarberSchedule schedule) {
        return repository.save(schedule);
    }

    public Optional<BarberSchedule> findById(Long id) {
        return repository.findById(id);
    }

    public List<BarberSchedule> findByBarber(Long barberId) {
        return repository.findByBarberId(barberId);
    }

    public List<BarberSchedule> findActiveByBarber(Long barberId) {
        return repository.findByBarberIdAndActiveTrue(barberId);
    }

    public BarberSchedule update(Long id, BarberSchedule schedule) {
        return repository.findById(id).map(existing -> {
            existing.setDayOfWeek(schedule.getDayOfWeek());
            existing.setStartTime(schedule.getStartTime());
            existing.setEndTime(schedule.getEndTime());
            existing.setActive(schedule.getActive());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Horario no encontrado: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
