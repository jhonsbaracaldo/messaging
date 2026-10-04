package efinomina.message.efinomina.domain.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WhatsAppNotificationService {

    private static final Logger log = LoggerFactory.getLogger(WhatsAppNotificationService.class);

    private final RestTemplate restTemplate;
    private final String serviceUrl;
    private final String destinationNumber;
    private final String groupId;
    private final boolean enabled;

    public WhatsAppNotificationService(
            RestTemplate restTemplate,
            @Value("${whatsapp.service.url}") String serviceUrl,
            @Value("${whatsapp.notifications.destination-number}") String destinationNumber,
            @Value("${whatsapp.notifications.group-id:}") String groupId,
            @Value("${whatsapp.notifications.enabled}") boolean enabled) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl;
        this.destinationNumber = destinationNumber;
        this.groupId = groupId;
        this.enabled = enabled;
    }

    public void enviarMensaje(String mensaje) {
        if (!enabled) return;

        boolean tieneNumero = destinationNumber != null && !destinationNumber.isBlank();
        boolean tieneGrupo  = groupId != null && !groupId.isBlank();

        if (!tieneNumero && !tieneGrupo) return;

        Map<String, String> body = new HashMap<>();
        body.put("message", mensaje);
        if (tieneNumero) body.put("phone", destinationNumber);
        if (tieneGrupo)  body.put("groupId", groupId);

        try {
            restTemplate.postForEntity(serviceUrl + "/send-message", body, Void.class);
        } catch (RestClientException ex) {
            log.error("No se pudo enviar el mensaje de WhatsApp: {}", ex.getMessage());
        }
    }
}
