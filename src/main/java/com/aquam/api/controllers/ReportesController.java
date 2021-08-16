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

@CrossOrigin(
        value = "*"
)
@RestController
public class ReportesController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private ReportesService reportesService;

    @Autowired
    public ReportesController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }

    @GetMapping("/reports/{id}")
    public Reporte getReporte(@PathVariable(value = "id") String id){
        return this.reportesService.getReporte(Long.parseLong(id));
    }

    @GetMapping("/reports")
    public List<Reporte> getAllReportes(@RequestParam(required = false, value = "mail") String mail,
                                        @RequestParam(required = false, value = "barrio")  String barrio){
        return this.reportesService.getAllReportes(mail, barrio);
    }

    @PostMapping("/reports")
    public Reporte createReporte(@RequestBody Reporte reporte){
        Date fechaInicio = new Date();
        Date fechaFin = DateUtils.addDays(fechaInicio, 2);

        return this.reportesService.createReporte(reporte.getLatitud(), reporte.getLongitud(),
                reporte.getBarrio(), reporte.getMail(), reporte.getDescripcion(),
                fechaInicio, fechaFin, reporte.getPuntaje(), reporte.getCategoria());
    }

    @DeleteMapping("/reports/{id}")
    public Reporte deleteReporte(@PathVariable(value = "id") String id){
        return this.reportesService.deleteReporte(Long.parseLong(id));
    }

    @GetMapping("/is-critic")
    public boolean isCritic(@RequestParam(value = "barrio") String barrio){
        return this.reportesService.isCritic(barrio);
    }
}
