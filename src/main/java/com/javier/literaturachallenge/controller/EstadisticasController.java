package com.javier.literaturachallenge.controller;

import com.javier.literaturachallenge.services.EstadisticasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EstadisticasController {

    private final EstadisticasService estadisticasService;

    public EstadisticasController(EstadisticasService estadisticasService) {
        this.estadisticasService = estadisticasService;
    }

    @GetMapping("/estadisticas/idiomas")
    public Map<String, Long> obtenerEstadisticasPorIdioma() {
        return estadisticasService.obtenerCantidadPorIdioma();
    }


}