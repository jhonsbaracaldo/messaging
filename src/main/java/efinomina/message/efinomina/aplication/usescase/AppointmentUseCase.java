package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.AppointmentDTO;
import efinomina.message.efinomina.domain.model.entity.Appointment;
import efinomina.message.efinomina.infraestructure.mapper.AppointmentMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryAppointment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentUseCase {

    private final RepositoryAppointment repositoryAppointment;

    public AppointmentUseCase(RepositoryAppointment repositoryAppointment) {
        this.repositoryAppointment = repositoryAppointment;
    }

    public AppointmentDTO crearCita(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setClientId(appointmentDTO.getClientId());
        appointment.setBarberId(appointmentDTO.getBarberId());
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setStartTime(appointmentDTO.getStartTime());
        appointment.setEndTime(appointmentDTO.getEndTime());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setNotes(appointmentDTO.getNotes());
        appointment.setTotal(appointmentDTO.getTotal());
        appointment.setCreatedById(appointmentDTO.getCreatedById());
        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setUpdatedAt(LocalDateTime.now());

        efinomina.message.efinomina.infraestructure.persistence.entity.Appointment saved =
                repositoryAppointment.save(AppointmentMapper.toEntity(appointment));
        return AppointmentMapper.toDTO(AppointmentMapper.toDomain(saved));
    }

    public Optional<AppointmentDTO> obtenerCitaPorId(Long id) {
        return repositoryAppointment.findById(id)
                .map(e -> AppointmentMapper.toDTO(AppointmentMapper.toDomain(e)));
    }

    public List<AppointmentDTO> obtenerTodasLasCitas() {
        return repositoryAppointment.findAll()
                .stream()
                .map(e -> AppointmentMapper.toDTO(AppointmentMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public AppointmentDTO actualizarCita(Long id, AppointmentDTO appointmentDTO) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Appointment> existente =
                repositoryAppointment.findById(id);
        if (existente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Appointment e = existente.get();
            e.setAppointmentDate(appointmentDTO.getAppointmentDate());
            e.setStartTime(appointmentDTO.getStartTime());
            e.setEndTime(appointmentDTO.getEndTime());
            e.setStatus(appointmentDTO.getStatus());
            e.setNotes(appointmentDTO.getNotes());
            e.setTotal(appointmentDTO.getTotal());
            e.setUpdatedAt(LocalDateTime.now());
            return AppointmentMapper.toDTO(AppointmentMapper.toDomain(repositoryAppointment.save(e)));
        }
        return null;
    }

    public void eliminarCita(Long id) {
        repositoryAppointment.deleteById(id);
    }

    public List<AppointmentDTO> obtenerCitasPorCliente(Long clientId) {
        return repositoryAppointment.findByClientId(clientId)
                .stream()
                .map(e -> AppointmentMapper.toDTO(AppointmentMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> obtenerCitasPorBarbero(Long barberId) {
        return repositoryAppointment.findByBarberId(barberId)
                .stream()
                .map(e -> AppointmentMapper.toDTO(AppointmentMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> obtenerCitasPorFecha(LocalDate fecha) {
        return repositoryAppointment.findByAppointmentDate(fecha)
                .stream()
                .map(e -> AppointmentMapper.toDTO(AppointmentMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> obtenerCitasPorEstado(String status) {
        return repositoryAppointment.findByStatus(status)
                .stream()
                .map(e -> AppointmentMapper.toDTO(AppointmentMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> obtenerCitasPorBarberoYFecha(Long barberId, LocalDate fecha) {
        return repositoryAppointment.findByBarberIdAndAppointmentDate(barberId, fecha)
                .stream()
                .map(e -> AppointmentMapper.toDTO(AppointmentMapper.toDomain(e)))
                .collect(Collectors.toList());
    }
}
