package com.cmms.services;

import com.cmms.entities.Taller;
import com.cmms.repositories.TallerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TallerService {

    private final TallerRepository tallerRepository;

    public List<Taller> obtenerTodos() {
        return tallerRepository.findAll();
    }

    public Taller obtenerPorId(Integer id) {
        return tallerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Taller no encontrado con id: " + id));
    }

    public Taller guardar(Taller taller) {
        return tallerRepository.save(taller);
    }

    public void eliminar(Integer id) {
        tallerRepository.deleteById(id);
    }
}