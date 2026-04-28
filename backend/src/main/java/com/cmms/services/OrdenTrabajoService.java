package com.cmms.services;

import com.cmms.entities.OrdenTrabajo;
import com.cmms.repositories.OrdenTrabajoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenTrabajoService {

    private final OrdenTrabajoRepository ordenTrabajoRepository;

    public List<OrdenTrabajo> obtenerTodos() {
        return ordenTrabajoRepository.findAll();
    }

    public OrdenTrabajo obtenerPorId(Integer id) {
        return ordenTrabajoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden de trabajo no encontrada con id: " + id));
    }

    public List<OrdenTrabajo> obtenerPorEstado(OrdenTrabajo.EstadoOtEnum estado) {
        return ordenTrabajoRepository.findByEstado(estado);
    }

    public List<OrdenTrabajo> obtenerPorActivo(Integer idActivo) {
        return ordenTrabajoRepository.findByActivo_IdActivo(idActivo);
    }

    public OrdenTrabajo guardar(OrdenTrabajo ot) {
        return ordenTrabajoRepository.save(ot);
    }

    public OrdenTrabajo cerrarOrden(Integer id) {
        OrdenTrabajo ot = obtenerPorId(id);
        ot.setEstado(OrdenTrabajo.EstadoOtEnum.FINALIZADA);
        ot.setFechaFinalizacion(LocalDateTime.now());
        return ordenTrabajoRepository.save(ot);
    }

    public void eliminar(Integer id) {
        ordenTrabajoRepository.deleteById(id);
    }
}