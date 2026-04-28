package com.cmms.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ordenes_trabajo")
public class OrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ot")
    private Integer idOt;

    @ManyToOne
    @JoinColumn(name = "id_activo", nullable = false)
    private Activo activo;

    @ManyToOne
    @JoinColumn(name = "id_plan")
    private PlanPreventivo planPreventivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_ot", nullable = false)
    private TipoOtEnum tipoOt;

    @Column(name = "motivo_tarea")
    private String motivoTarea;

    @Column(name = "tarea_realizada")
    private String tareaRealizada;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "fecha_recepcion")
    private LocalDateTime fechaRecepcion;

    @Column(name = "fecha_finalizacion")
    private LocalDateTime fechaFinalizacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoOtEnum estado;

    public enum TipoOtEnum {
        CORRECTIVA, PREVENTIVA
    }

    public enum EstadoOtEnum {
        ABIERTA, EN_PROCESO, FINALIZADA
    }
}