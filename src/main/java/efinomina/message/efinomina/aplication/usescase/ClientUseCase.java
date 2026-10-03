package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.ClientDTO;
import efinomina.message.efinomina.domain.model.entity.Client;
import efinomina.message.efinomina.domain.services.GoogleSheetsNotificationService;
import efinomina.message.efinomina.domain.services.WhatsAppNotificationService;
import efinomina.message.efinomina.infraestructure.mapper.ClientMapper;
import efinomina.message.efinomina.infraestructure.persistence.entity.Barber;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryBarber;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientUseCase {

    private final RepositoryClient repositoryClient;
    private final RepositoryBarber repositoryBarber;
    private final WhatsAppNotificationService whatsAppNotificationService;
    private final GoogleSheetsNotificationService googleSheetsNotificationService;

    public ClientUseCase(
            RepositoryClient repositoryClient,
            RepositoryBarber repositoryBarber,
            WhatsAppNotificationService whatsAppNotificationService,
            GoogleSheetsNotificationService googleSheetsNotificationService) {
        this.repositoryClient = repositoryClient;
        this.repositoryBarber = repositoryBarber;
        this.whatsAppNotificationService = whatsAppNotificationService;
        this.googleSheetsNotificationService = googleSheetsNotificationService;
    }

    public ClientDTO crearCliente(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        client.setPhone(clientDTO.getPhone());
        client.setEmail(clientDTO.getEmail());
        client.setNotes(clientDTO.getNotes());
        client.setActive(true);
        client.setCreatedAt(LocalDateTime.now());
        client.setPreferredBarberId(clientDTO.getBarberId());

        efinomina.message.efinomina.infraestructure.persistence.entity.Client saved =
                repositoryClient.save(ClientMapper.toEntity(client));
        ClientDTO creado = ClientMapper.toDTO(ClientMapper.toDomain(saved));

        String nombreBarbero = obtenerNombreBarbero(creado.getBarberId());

        String mensaje = String.format(
                "Nuevo cliente registrado:%nNombre: %s %s%nTelefono: %s%nEmail: %s%nNotas: %s%nBarbero preferido: %s",
                creado.getName(), creado.getLastName(), creado.getPhone(), creado.getEmail(), creado.getNotes(), nombreBarbero);
        whatsAppNotificationService.enviarMensaje(mensaje);
        googleSheetsNotificationService.registrarCliente(
                creado.getName(), creado.getLastName(), creado.getPhone(), creado.getEmail(), creado.getNotes(), nombreBarbero);

        return creado;
    }

    private String obtenerNombreBarbero(Long barberId) {
        if (barberId == null) {
            return "Sin preferencia";
        }
        return repositoryBarber.findById(barberId)
                .map(Barber::getUser)
                .map(u -> u.getName() + " " + u.getLastName())
                .orElse("Sin preferencia");
    }

    public Optional<ClientDTO> obtenerClientePorId(Long id) {
        return repositoryClient.findById(id)
                .map(e -> ClientMapper.toDTO(ClientMapper.toDomain(e)));
    }

    public List<ClientDTO> obtenerTodosLosClientes() {
        return repositoryClient.findAll()
                .stream()
                .map(e -> ClientMapper.toDTO(ClientMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public ClientDTO actualizarCliente(Long id, ClientDTO clientDTO) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Client> existente =
                repositoryClient.findById(id);
        if (existente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Client e = existente.get();
            e.setName(clientDTO.getName());
            e.setLastName(clientDTO.getLastName());
            e.setPhone(clientDTO.getPhone());
            e.setEmail(clientDTO.getEmail());
            e.setNotes(clientDTO.getNotes());
            if (clientDTO.getActive() != null) e.setActive(clientDTO.getActive());
            e.setPreferredBarberId(clientDTO.getBarberId());
            return ClientMapper.toDTO(ClientMapper.toDomain(repositoryClient.save(e)));
        }
        return null;
    }

    public void eliminarCliente(Long id) {
        repositoryClient.deleteById(id);
    }

    public Optional<ClientDTO> obtenerClientePorEmail(String email) {
        return repositoryClient.findByEmail(email)
                .map(e -> ClientMapper.toDTO(ClientMapper.toDomain(e)));
    }

    public Optional<ClientDTO> obtenerClientePorTelefono(String phone) {
        return repositoryClient.findByPhone(phone)
                .map(e -> ClientMapper.toDTO(ClientMapper.toDomain(e)));
    }

    public List<ClientDTO> obtenerClientesActivos() {
        return repositoryClient.findByActiveTrue()
                .stream()
                .map(e -> ClientMapper.toDTO(ClientMapper.toDomain(e)))
                .collect(Collectors.toList());
    }
}
