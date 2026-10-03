package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.Appointment;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryAppointment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentMgmtService {

    private final RepositoryAppointment repository;

    public AppointmentMgmtService(RepositoryAppointment repository) {
        this.repository = repository;
    }

    public Appointment save(Appointment appointment) {
        appointment.setStatus("PENDING");
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setUpdatedAt(LocalDateTime.now());
        return repository.save(appointment);
    }

    public Optional<Appointment> findById(Long id) {
        return repository.findById(id);
    }

    public List<Appointment> findAll() {
        return repository.findAll();
    }

    public List<Appointment> findByClient(Long clientId) {
        return repository.findByClientId(clientId);
    }

    public List<Appointment> findByBarber(Long barberId) {
        return repository.findByBarberId(barberId);
    }

    public List<Appointment> findByDate(LocalDate date) {
        return repository.findByAppointmentDate(date);
    }

    public List<Appointment> findByBarberAndDate(Long barberId, LocalDate date) {
        return repository.findByBarberIdAndAppointmentDate(barberId, date);
    }

    public Appointment updateStatus(Long id, String status) {
        return repository.findById(id).map(existing -> {
            existing.setStatus(status);
            existing.setUpdatedAt(LocalDateTime.now());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada: " + id));
    }

    public Appointment update(Long id, Appointment appointment) {
        return repository.findById(id).map(existing -> {
            existing.setAppointmentDate(appointment.getAppointmentDate());
            existing.setStartTime(appointment.getStartTime());
            existing.setEndTime(appointment.getEndTime());
            existing.setNotes(appointment.getNotes());
            existing.setTotal(appointment.getTotal());
            existing.setUpdatedAt(LocalDateTime.now());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
