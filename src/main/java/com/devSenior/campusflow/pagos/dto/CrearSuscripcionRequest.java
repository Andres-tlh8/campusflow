package com.devSenior.campusflow.pagos.dto;

import com.devSenior.campusflow.pagos.model.PlanSuscripcion;
import jakarta.validation.constraints.NotNull;

public class CrearSuscripcionRequest {

    @NotNull
    private PlanSuscripcion plan;

    public PlanSuscripcion getPlan() {
        return plan;
    }

    public void setPlan(PlanSuscripcion plan) {
        this.plan = plan;

        
    }
}