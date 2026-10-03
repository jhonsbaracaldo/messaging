package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.BarberVacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryBarberVacation extends JpaRepository<BarberVacation, Long> {
    List<BarberVacation> findByBarberId(Long barberId);
}
