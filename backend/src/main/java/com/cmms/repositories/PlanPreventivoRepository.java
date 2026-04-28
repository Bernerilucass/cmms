package com.cmms.repositories;

import com.cmms.entities.PlanPreventivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanPreventivoRepository extends JpaRepository<PlanPreventivo, Integer> {
    List<PlanPreventivo> findByActivoTrue();
}