package com.cmms.controllers;

import com.cmms.entities.Taller;
import com.cmms.services.TallerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/talleres")
@RequiredArgsConstructor
public class TallerController {

    private final TallerService tallerService;

    @GetMapping
    public ResponseEntity<List<Taller>> obtenerTodos() {
        return ResponseEntity.ok(tallerService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taller> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tallerService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Taller> crear(@RequestBody Taller taller) {
        return ResponseEntity.ok(tallerService.guardar(taller));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Taller> actualizar(@PathVariable Integer id, @RequestBody Taller taller) {
        taller.setIdTaller(id);
        return ResponseEntity.ok(tallerService.guardar(taller));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tallerService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}