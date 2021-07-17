package com.aquam.api.services;

import com.aquam.api.entities.Reporte;

import java.util.Date;
import java.util.List;

public interface ReportesService {

    Reporte getReporte(long id);

    List<Reporte> getAllReportes();

    Reporte createReporte(String latitud, String longitud, String barrio,
                          String mail, String descripcion, Date fechaInicio, Date fechaFin,
                          int puntaje, String categoria);




}
