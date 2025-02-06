package efinomina.message.efinomina.infraestructure;

import efinomina.message.efinomina.domain.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUserVerbs extends JpaRepository<User,Integer> {
    User save(User user);
    User update(User user);
    void delete(Integer id);
}
