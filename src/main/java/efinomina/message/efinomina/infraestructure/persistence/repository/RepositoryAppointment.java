package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositoryAppointment extends JpaRepository<Appointment, Long> {
    List<Appointment> findByClientId(Long clientId);
    List<Appointment> findByBarberId(Long barberId);
    List<Appointment> findByAppointmentDate(LocalDate date);
    List<Appointment> findByStatus(String status);
    List<Appointment> findByBarberIdAndAppointmentDate(Long barberId, LocalDate date);
}
