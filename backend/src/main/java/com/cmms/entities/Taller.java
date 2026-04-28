package com.cmms.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "talleres")
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_taller")
    private Integer idTaller;

    @Column(name = "nombre_taller", nullable = false, length = 50)
    private String nombreTaller;
}