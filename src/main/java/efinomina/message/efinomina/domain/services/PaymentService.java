package efinomina.message.efinomina.aplication.services;

import efinomina.message.efinomina.infraestructure.persistence.entity.Payment;
import efinomina.message.efinomina.infraestructure.persistence.repository.RepositoryPayment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final RepositoryPayment repository;

    public PaymentService(RepositoryPayment repository) {
        this.repository = repository;
    }

    public Payment save(Payment payment) {
        payment.setPaymentStatus("PENDING");
        payment.setCreatedAt(LocalDateTime.now());
        return repository.save(payment);
    }

    public Optional<Payment> findById(Long id) {
        return repository.findById(id);
    }

    public List<Payment> findAll() {
        return repository.findAll();
    }

    public Optional<Payment> findByAppointment(Long appointmentId) {
        return repository.findByAppointmentId(appointmentId);
    }

    public Payment markAsPaid(Long id) {
        return repository.findById(id).map(existing -> {
            existing.setPaymentStatus("PAID");
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Pago no encontrado: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
