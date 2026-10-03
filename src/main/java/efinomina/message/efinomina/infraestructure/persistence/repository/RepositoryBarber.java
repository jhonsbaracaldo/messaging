package efinomina.message.efinomina.infraestructure.persistence.repository;

import efinomina.message.efinomina.infraestructure.persistence.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryBarber extends JpaRepository<Barber, Long> {
    List<Barber> findByActiveTrue();
    List<Barber> findByUserId(Long userId);


    Optional<Barber> findByPhone(String phone);
}
