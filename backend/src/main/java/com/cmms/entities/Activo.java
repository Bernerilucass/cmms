package com.cmms.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "activos")
public class Activo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activo")
    private Integer idActivo;

    @Column(name = "nombre_activo", nullable = false, length = 100)
    private String nombreActivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "criticidad", nullable = false)
    private CriticidadEnum criticidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_reparacion", nullable = false)
    private TipoReparacionEnum tipoReparacion;

    @ManyToOne
    @JoinColumn(name = "id_taller")
    private Taller taller;

    public enum CriticidadEnum {
        ALTA, MEDIA, BAJA
    }

    public enum TipoReparacionEnum {
        INTERNA, EXTERNA
    }
}