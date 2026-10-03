package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.AppointmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryAppointmentHistory extends JpaRepository<AppointmentHistory, Long> {
    List<AppointmentHistory> findByAppointmentId(Long appointmentId);
}
