package efinomina.message.efinomina.infraestructure;

import efinomina.message.efinomina.domain.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RepositoryUserSearch extends JpaRepository<User,Integer> {


    Optional<User> findById(Integer integer);
    Optional<User> findByName (String name);
    List<User> findAll();
}
