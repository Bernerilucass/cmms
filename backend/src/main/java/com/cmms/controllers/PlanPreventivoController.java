package com.cmms.controllers;

import com.cmms.entities.PlanPreventivo;
import com.cmms.services.PlanPreventivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/planes")
@RequiredArgsConstructor
public class PlanPreventivoController {

    private final PlanPreventivoService planPreventivoService;

    @GetMapping
    public ResponseEntity<List<PlanPreventivo>> obtenerTodos() {
        return ResponseEntity.ok(planPreventivoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanPreventivo> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(planPreventivoService.obtenerPorId(id));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<PlanPreventivo>> obtenerActivos() {
        return ResponseEntity.ok(planPreventivoService.obtenerActivos());
    }

    @PostMapping
    public ResponseEntity<PlanPreventivo> crear(@RequestBody PlanPreventivo plan) {
        return ResponseEntity.ok(planPreventivoService.guardar(plan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanPreventivo> actualizar(@PathVariable Integer id,
                                                     @RequestBody PlanPreventivo plan) {
        plan.setIdPlan(id);
        return ResponseEntity.ok(planPreventivoService.guardar(plan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        planPreventivoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}