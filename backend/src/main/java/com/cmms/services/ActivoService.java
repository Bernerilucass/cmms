package com.cmms.services;

import com.cmms.entities.Activo;
import com.cmms.repositories.ActivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivoService {

    private final ActivoRepository activoRepository;

    public List<Activo> obtenerTodos() {
        return activoRepository.findAll();
    }

    public Activo obtenerPorId(Integer id) {
        return activoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activo no encontrado con id: " + id));
    }

    public Activo guardar(Activo activo) {
        return activoRepository.save(activo);
    }

    public void eliminar(Integer id) {
        activoRepository.deleteById(id);
    }
}