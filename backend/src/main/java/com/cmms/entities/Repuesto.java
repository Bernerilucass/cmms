package com.cmms.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "repuestos")
public class Repuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repuesto")
    private Integer idRepuesto;

    @ManyToOne
    @JoinColumn(name = "id_ot", nullable = false)
    private OrdenTrabajo ordenTrabajo;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}