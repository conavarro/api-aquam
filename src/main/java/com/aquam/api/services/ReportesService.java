package com.aquam.api.services;

import com.aquam.api.entities.Reporte;

import java.util.Date;
import java.util.List;

public interface ReportesService {

    Reporte getReporte(long id);

    List<Reporte> getAllReportes(String mail);

    Reporte createReporte(float latitud, float longitud, String barrio,
                          String mail, String descripcion, Date fechaInicio, Date fechaFin,
                          int puntaje, String categoria);




}
