package com.aquam.api.controllers;

import com.aquam.api.entities.Reporte;
import com.aquam.api.services.ReportesService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ReportesController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private ReportesService reportesService;

    @Autowired
    public ReportesController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }

    @GetMapping("/reports/{id}")
    public Reporte getReporte(@PathVariable(name = "id") String id){
        return this.reportesService.getReporte(Long.parseLong(id));
    }

    @GetMapping("/reports")
    public List<Reporte> getAllReporte(){
        LOGGER.info(String.valueOf(new Date()));
        return this.reportesService.getAllReportes();
    }

    @PostMapping("/reports")
    public Reporte createReporte(@RequestBody Reporte reporte){
        Date fechaInicio = new Date();
        Date fechaFin = DateUtils.addDays(fechaInicio, 2);

        return this.reportesService.createReporte(reporte.getLatitud(), reporte.getLongitud(),
                reporte.getBarrio(), reporte.getMail(), reporte.getDescripcion(),
                fechaInicio, fechaFin, reporte.getPuntaje(), reporte.getCategoria());
    }
}
