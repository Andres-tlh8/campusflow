package com.devSenior.campusflow.pagos.service;

import com.devSenior.campusflow.pagos.model.EventoStripe;
import com.devSenior.campusflow.pagos.repository.EventoStripeRepository;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class WebhookService {

    @Value("${stripe.webhook-secret}")
    private String webhookSecret;

    private final EventoStripeRepository eventoStripeRepository;

    public WebhookService(EventoStripeRepository eventoStripeRepository) {
        this.eventoStripeRepository = eventoStripeRepository;
    }

    @Transactional
    public void procesar(String payload, String firma) throws SignatureVerificationException {
        Event event = Webhook.constructEvent(payload, firma, webhookSecret);

        if (eventoStripeRepository.existsByStripeEventId(event.getId())) {
            return;
        }

        EventoStripe evento = new EventoStripe();
        evento.setStripeEventId(event.getId());
        evento.setTipo(event.getType());
        evento.setProcesado(false);
        evento.setRecibidoEn(LocalDateTime.now());
        eventoStripeRepository.save(evento);

        // aquí luego: if(event.getType().equals("checkout.session.completed"))...

        evento.setProcesado(true);
        eventoStripeRepository.save(evento);
    }
}