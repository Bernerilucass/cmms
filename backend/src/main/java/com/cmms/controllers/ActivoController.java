package com.cmms.controllers;

import com.cmms.entities.Activo;
import com.cmms.services.ActivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activos")
@RequiredArgsConstructor
public class ActivoController {

    private final ActivoService activoService;

    @GetMapping
    public ResponseEntity<List<Activo>> obtenerTodos() {
        return ResponseEntity.ok(activoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activo> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(activoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Activo> crear(@RequestBody Activo activo) {
        return ResponseEntity.ok(activoService.guardar(activo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activo> actualizar(@PathVariable Integer id, @RequestBody Activo activo) {
        activo.setIdActivo(id);
        return ResponseEntity.ok(activoService.guardar(activo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        activoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}