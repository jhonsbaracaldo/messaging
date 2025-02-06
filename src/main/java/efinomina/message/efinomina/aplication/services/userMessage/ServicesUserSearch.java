package efinomina.message.efinomina.aplication.services.userMessage;

import efinomina.message.efinomina.infraestructure.RepositoryUserSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesUserSearch {

    @Autowired
    private final RepositoryUserSearch repositoryUserSearch;


    public ServicesUserSearch(RepositoryUserSearch repositoryUserSearch) {
        this.repositoryUserSearch = repositoryUserSearch;
    }

    public List<RepositoryUserSearch> getUserId() {
//        return this.repositoryUserSearch.findById();
        return null;
    }

    public Optional<RepositoryUserSearch> getUserName() {
//        return this.repositoryUserSearch.findByName(getUser);
        return null;
    }

    public Optional<RepositoryUserSearch> allUser(){
//        return this.repositoryUserSearch.findAll();
        return null;
    }
}
