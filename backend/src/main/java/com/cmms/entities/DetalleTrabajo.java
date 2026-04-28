package com.cmms.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "detalle_trabajo")
public class DetalleTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_ot", nullable = false)
    private OrdenTrabajo ordenTrabajo;

    @ManyToOne
    @JoinColumn(name = "id_usuarios", nullable = false)
    private Usuario usuario;

    @Column(name = "horas_hombre", precision = 5, scale = 2)
    private BigDecimal horasHombre;
}