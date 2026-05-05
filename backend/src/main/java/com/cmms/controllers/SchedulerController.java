package com.cmms.controllers;

import com.cmms.scheduler.PreventivosScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scheduler")
@RequiredArgsConstructor
public class SchedulerController {

    private final PreventivosScheduler preventivosScheduler;

    @PostMapping("/ejecutar")
    public ResponseEntity<String> ejecutarManualmente() {
        preventivosScheduler.generarOtsPreventivas();
        return ResponseEntity.ok("Scheduler ejecutado correctamente");
    }
}