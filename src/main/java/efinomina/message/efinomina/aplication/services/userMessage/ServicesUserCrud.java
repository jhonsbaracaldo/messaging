package efinomina.message.efinomina.aplication.services.userMessage;

import efinomina.message.efinomina.infraestructure.RepositoryUserVerbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServicesUserCrud {
    @Autowired
     private final RepositoryUserVerbs repositoryUserVerbs;

    public ServicesUserCrud(RepositoryUserVerbs repositoryUserVerbs) {
        this.repositoryUserVerbs = repositoryUserVerbs;
    }

    public ResponseEntity<RepositoryUserVerbs> newUser (String User){
//            Optional<RepositoryUserVerbs> request = repositoryUserVerbs.save(setUser);
//            return new ResponseEntity<>(request, HttpStatus.CREATED,"creado");
        return null;
    }
}
