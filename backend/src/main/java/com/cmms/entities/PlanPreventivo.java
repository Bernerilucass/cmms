package com.cmms.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "planes_preventivos")
public class PlanPreventivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan")
    private Integer idPlan;

    @ManyToOne
    @JoinColumn(name = "id_activo", nullable = false)
    private Activo activo;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "intervalo_dias", nullable = false)
    private Integer intervaloDias;

    @Column(name = "ultimo_mantenimiento")
    private LocalDate ultimoMantenimiento;

    @Column(name = "proximo_mantenimiento")
    private LocalDate proximoMantenimiento;

    // FIX del error de duplicación
    @Column(name = "activo")
    private Boolean activoFlag;

    // Mejora automática
    @PrePersist
    @PreUpdate
    public void calcularProximoMantenimiento() {
        if (ultimoMantenimiento != null && intervaloDias != null) {
            this.proximoMantenimiento = ultimoMantenimiento.plusDays(intervaloDias);
        }
    }
}