package com.devSenior.campusflow.pagos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos_stripe")
public class EventoStripe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String stripeEventId;
    private String tipo;
    private LocalDateTime recibidoEn;
    private Boolean procesado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStripeEventId() { return stripeEventId; }
    public void setStripeEventId(String s) { this.stripeEventId = s; }
    public String getTipo() { return tipo; }
    public void setTipo(String t) { this.tipo = t; }
    public LocalDateTime getRecibidoEn() { return recibidoEn; }
    public void setRecibidoEn(LocalDateTime r) { this.recibidoEn = r; }
    public Boolean getProcesado() { return procesado; }
    public void setProcesado(Boolean p) { this.procesado = p; }
}