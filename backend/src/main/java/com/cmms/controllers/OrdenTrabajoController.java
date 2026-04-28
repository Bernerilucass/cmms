package com.cmms.controllers;

import com.cmms.entities.OrdenTrabajo;
import com.cmms.services.OrdenTrabajoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
public class OrdenTrabajoController {

    private final OrdenTrabajoService ordenTrabajoService;

    @GetMapping
    public ResponseEntity<List<OrdenTrabajo>> obtenerTodos() {
        return ResponseEntity.ok(ordenTrabajoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ordenTrabajoService.obtenerPorId(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<OrdenTrabajo>> obtenerPorEstado(
            @PathVariable OrdenTrabajo.EstadoOtEnum estado) {
        return ResponseEntity.ok(ordenTrabajoService.obtenerPorEstado(estado));
    }

    @GetMapping("/activo/{idActivo}")
    public ResponseEntity<List<OrdenTrabajo>> obtenerPorActivo(@PathVariable Integer idActivo) {
        return ResponseEntity.ok(ordenTrabajoService.obtenerPorActivo(idActivo));
    }

    @PostMapping
    public ResponseEntity<OrdenTrabajo> crear(@RequestBody OrdenTrabajo ot) {
        return ResponseEntity.ok(ordenTrabajoService.guardar(ot));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenTrabajo> actualizar(@PathVariable Integer id,
                                                   @RequestBody OrdenTrabajo ot) {
        ot.setIdOt(id);
        return ResponseEntity.ok(ordenTrabajoService.guardar(ot));
    }

    @PutMapping("/{id}/cerrar")
    public ResponseEntity<OrdenTrabajo> cerrar(@PathVariable Integer id) {
        return ResponseEntity.ok(ordenTrabajoService.cerrarOrden(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ordenTrabajoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}