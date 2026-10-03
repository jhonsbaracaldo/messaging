package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.BarberSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryBarberSchedule extends JpaRepository<BarberSchedule, Long> {
    List<BarberSchedule> findByBarberId(Long barberId);
    List<BarberSchedule> findByBarberIdAndActiveTrue(Long barberId);
}
