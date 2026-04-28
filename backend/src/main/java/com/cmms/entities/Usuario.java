package com.cmms.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private Integer idUsuarios;

    @Column(name = "legajo", nullable = false, unique = true)
    private Integer legajo;

    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RolEnum rol;

    @Column(name = "fecha_alta", updatable = false)
    private LocalDateTime fechaAlta;

    // 🔧 Set automático como en la BD
    @PrePersist
    public void prePersist() {
        this.fechaAlta = LocalDateTime.now();
    }

    public enum RolEnum {
        ADMINISTRADOR, TECNICO
    }
}