package efinomina.message.efinomina.aplication.usescase;

import efinomina.message.efinomina.aplication.dto.PaymentDTO;
import efinomina.message.efinomina.domain.model.entity.Payment;
import efinomina.message.efinomina.infraestructure.mapper.PaymentMapper;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryPayment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentUseCase {

    private final RepositoryPayment repositoryPayment;

    public PaymentUseCase(RepositoryPayment repositoryPayment) {
        this.repositoryPayment = repositoryPayment;
    }

    public PaymentDTO crearPago(PaymentDTO dto) {
        Payment p = new Payment();
        p.setAppointmentId(dto.getAppointmentId());
        p.setTotal(dto.getTotal());
        p.setPaymentMethod(dto.getPaymentMethod());
        p.setPaymentStatus(dto.getPaymentStatus());
        p.setTransactionReference(dto.getTransactionReference());
        p.setCreatedById(dto.getCreatedById());
        p.setCreatedAt(LocalDateTime.now());
        return PaymentMapper.toDTO(PaymentMapper.toDomain(repositoryPayment.save(PaymentMapper.toEntity(p))));
    }

    public Optional<PaymentDTO> obtenerPagoPorId(Long id) {
        return repositoryPayment.findById(id).map(e -> PaymentMapper.toDTO(PaymentMapper.toDomain(e)));
    }

    public List<PaymentDTO> obtenerTodosLosPagos() {
        return repositoryPayment.findAll().stream()
                .map(e -> PaymentMapper.toDTO(PaymentMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public PaymentDTO actualizarPago(Long id, PaymentDTO dto) {
        Optional<efinomina.message.efinomina.infraestructure.persistence.entity.Payment> existente = repositoryPayment.findById(id);
        if (existente.isPresent()) {
            efinomina.message.efinomina.infraestructure.persistence.entity.Payment e = existente.get();
            e.setTotal(dto.getTotal());
            e.setPaymentMethod(dto.getPaymentMethod());
            e.setPaymentStatus(dto.getPaymentStatus());
            e.setTransactionReference(dto.getTransactionReference());
            return PaymentMapper.toDTO(PaymentMapper.toDomain(repositoryPayment.save(e)));
        }
        return null;
    }

    public void eliminarPago(Long id) {
        repositoryPayment.deleteById(id);
    }

    public Optional<PaymentDTO> obtenerPagoPorCita(Long appointmentId) {
        return repositoryPayment.findByAppointmentId(appointmentId)
                .map(e -> PaymentMapper.toDTO(PaymentMapper.toDomain(e)));
    }

    public List<PaymentDTO> obtenerPagosPorEstado(String paymentStatus) {
        return repositoryPayment.findByPaymentStatus(paymentStatus).stream()
                .map(e -> PaymentMapper.toDTO(PaymentMapper.toDomain(e)))
                .collect(Collectors.toList());
    }

    public List<PaymentDTO> obtenerPagosPorMetodo(String paymentMethod) {
        return repositoryPayment.findByPaymentMethod(paymentMethod).stream()
                .map(e -> PaymentMapper.toDTO(PaymentMapper.toDomain(e)))
                .collect(Collectors.toList());
    }
}
