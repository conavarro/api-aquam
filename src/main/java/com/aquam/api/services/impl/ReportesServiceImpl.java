package com.aquam.api.services.impl;

import com.aquam.api.entities.Reporte;
import com.aquam.api.repositories.ReportesRepository;
import com.aquam.api.services.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReportesServiceImpl implements ReportesService {

    private ReportesRepository reportesRepository;

    @Autowired
    public ReportesServiceImpl(ReportesRepository reportesRepository) {
        this.reportesRepository = reportesRepository;
    }

    @Override
    public Reporte getReporte(long id) {
        return reportesRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Reporte> getAllReportes() {
        return this.reportesRepository.getAllActiveReports(new Date());
    }

    @Override
    public Reporte createReporte(float latitud, float longitud, String barrio, String mail, String descripcion, Date fechaInicio, Date fechaFin, int puntaje, String categoria) {
        Reporte reporte = new Reporte();
        reporte.setBarrio(barrio);
        reporte.setCategoria(categoria);
        reporte.setDescripcion(descripcion);
        reporte.setFechaFin(fechaFin);
        reporte.setFechaInicio(fechaInicio);
        reporte.setLongitud(longitud);
        reporte.setLatitud(latitud);
        reporte.setMail(mail);
        reporte.setPuntaje(puntaje);
        return this.reportesRepository.save(reporte);
    }
}
