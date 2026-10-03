package efinomina.message.efinomina.domain.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GoogleSheetsNotificationService {

    private static final Logger log = LoggerFactory.getLogger(GoogleSheetsNotificationService.class);

    private final RestTemplate restTemplate;
    private final String webhookUrl;
    private final boolean enabled;

    public GoogleSheetsNotificationService(
            RestTemplate restTemplate,
            @Value("${google.sheets.webhook.url}") String webhookUrl,
            @Value("${google.sheets.webhook.enabled}") boolean enabled) {
        this.restTemplate = restTemplate;
        this.webhookUrl = webhookUrl;
        this.enabled = enabled;
    }

    public void registrarCliente(String name, String lastName, String phone, String email, String notes, String barberName) {
        if (!enabled || webhookUrl == null || webhookUrl.isBlank()) {
            return;
        }
        try {
            Map<String, String> body = Map.of(
                    "name", nullToEmpty(name),
                    "lastName", nullToEmpty(lastName),
                    "phone", nullToEmpty(phone),
                    "email", nullToEmpty(email),
                    "notes", nullToEmpty(notes),
                    "barberName", nullToEmpty(barberName));
            restTemplate.postForEntity(webhookUrl, body, String.class);
        } catch (RestClientException ex) {
            log.error("No se pudo registrar el cliente en Google Sheets: {}", ex.getMessage());
        }
    }

    private String nullToEmpty(String value) {
        return value == null ? "" : value;
    }
}
