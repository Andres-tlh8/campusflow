package com.devSenior.campusflow.pagos.repository;

import java.util.Optional;
import com.devSenior.campusflow.pagos.model.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {

    Optional<Suscripcion> findByUsuarioId(Long usuarioId);
}