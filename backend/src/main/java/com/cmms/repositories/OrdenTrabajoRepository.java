package com.cmms.repositories;

import com.cmms.entities.OrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenTrabajoRepository extends JpaRepository<OrdenTrabajo, Integer> {
    List<OrdenTrabajo> findByEstado(OrdenTrabajo.EstadoOtEnum estado);
    List<OrdenTrabajo> findByActivo_IdActivo(Integer idActivo);
}