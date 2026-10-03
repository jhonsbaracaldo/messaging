package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.Client;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final RepositoryClient repository;

    public ClientService(RepositoryClient repository) {
        this.repository = repository;
    }

    public Client save(Client client) {
        client.setActive(true);
        client.setCreatedAt(LocalDateTime.now());
        return repository.save(client);
    }

    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public List<Client> findAllActive() {
        return repository.findByActiveTrue();
    }

    public Client update(Long id, Client client) {
        return repository.findById(id).map(existing -> {
            existing.setName(client.getName());
            existing.setLastName(client.getLastName());
            existing.setPhone(client.getPhone());
            existing.setEmail(client.getEmail());
            existing.setNotes(client.getNotes());
            existing.setActive(client.getActive());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
