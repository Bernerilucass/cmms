package com.cmms.services;

import com.cmms.entities.PlanPreventivo;
import com.cmms.repositories.PlanPreventivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanPreventivoService {

    private final PlanPreventivoRepository planPreventivoRepository;

    public List<PlanPreventivo> obtenerTodos() {
        return planPreventivoRepository.findAll();
    }

    public PlanPreventivo obtenerPorId(Integer id) {
        return planPreventivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan preventivo no encontrado con id: " + id));
    }

    public List<PlanPreventivo> obtenerActivos() {
        return planPreventivoRepository.findByActivoTrue();
    }

    public PlanPreventivo guardar(PlanPreventivo plan) {
        return planPreventivoRepository.save(plan);
    }

    public void eliminar(Integer id) {
        planPreventivoRepository.deleteById(id);
    }
}