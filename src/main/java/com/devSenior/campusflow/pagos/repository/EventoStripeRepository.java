package com.devSenior.campusflow.pagos.repository;

import com.devSenior.campusflow.pagos.model.EventoStripe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoStripeRepository extends JpaRepository<EventoStripe, Long> {

    boolean existsByStripeEventId(String stripeEventId);
}