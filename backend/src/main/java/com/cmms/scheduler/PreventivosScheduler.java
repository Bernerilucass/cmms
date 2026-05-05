package com.cmms.scheduler;

import com.cmms.entities.OrdenTrabajo;
import com.cmms.entities.PlanPreventivo;
import com.cmms.repositories.OrdenTrabajoRepository;
import com.cmms.repositories.PlanPreventivoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PreventivosScheduler {

    private final PlanPreventivoRepository planPreventivoRepository;
    private final OrdenTrabajoRepository ordenTrabajoRepository;

    // Corre todos los días a las 8:00 AM
    @Scheduled(cron = "0 0 8 * * *")
    @Transactional
    public void generarOtsPreventivas() {
        log.info("=== Iniciando revisión de planes preventivos ===");

        List<PlanPreventivo> planesActivos = planPreventivoRepository.findByActivoFlagTrue();
        LocalDate hoy = LocalDate.now();
        int otGeneradas = 0;

        for (PlanPreventivo plan : planesActivos) {

            // Si no tiene fecha de próximo mantenimiento, la calculamos
            if (plan.getProximoMantenimiento() == null) {
                plan.setProximoMantenimiento(hoy);
            }

            // Verificar si venció el intervalo
            if (!plan.getProximoMantenimiento().isAfter(hoy)) {

                // Generar OT preventiva
                OrdenTrabajo ot = new OrdenTrabajo();
                ot.setActivo(plan.getActivo());
                ot.setPlanPreventivo(plan);
                ot.setTipoOt(OrdenTrabajo.TipoOtEnum.PREVENTIVA);
                ot.setMotivoTarea(plan.getDescripcion());
                ot.setEstado(OrdenTrabajo.EstadoOtEnum.ABIERTA);
                ot.setFechaRecepcion(LocalDateTime.now());

                ordenTrabajoRepository.save(ot);

                // Actualizar fechas del plan
                plan.setUltimoMantenimiento(hoy);
                plan.setProximoMantenimiento(hoy.plusDays(plan.getIntervaloDias()));
                planPreventivoRepository.save(plan);

                otGeneradas++;
                log.info("OT preventiva generada para activo: {} - Próximo mantenimiento: {}",
                        plan.getActivo().getNombreActivo(),
                        plan.getProximoMantenimiento());
            }
        }

        log.info("=== Revisión completada. OTs generadas: {} ===", otGeneradas);
    }

    // Para pruebas — corre cada 60 segundos
    // @Scheduled(fixedRate = 60000)
    // public void generarOtsPrevenativasPrueba() {
    //     generarOtsPreventivas();
    // }
}