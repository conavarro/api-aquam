package com.aquam.api.services.impl;

import com.aquam.api.dtos.CantidadReportesPorCategoria;
import com.aquam.api.entities.Reporte;
import com.aquam.api.repositories.ReportesRepository;
import com.aquam.api.services.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.aquam.api.constants.Constantes.DESAGUE_TAPADO;

@Service
@Transactional
public class ReportesServiceImpl implements ReportesService {

    private final ReportesRepository reportesRepository;
    private static final int minimaCantidadReportes = 20;

    @Autowired
    public ReportesServiceImpl(ReportesRepository reportesRepository) {
        this.reportesRepository = reportesRepository;
    }

    @Override
    public Reporte getReporte(long id) {
        return this.reportesRepository.findById(id).orElseThrow();
    }

    @Override
    public Reporte deleteReporte(long id) {
        Reporte reporte = getReporte(id);
        this.reportesRepository.delete(reporte);
        return reporte;
    }

//    @Override
//    public Reporte deleteReporte(long id) {
//        Reporte reporte = getReporte(id);
//        this.reportesRepository.deleteReporte(id);
//        return reporte;
//    }

    @Override
    public List<Reporte> getAllReportes(String mail, String barrio) {
        return this.reportesRepository.getAllActiveReports(barrio, mail, new Date());
    }

    @Override
    public Reporte createReporte(float latitud, float longitud, String barrio, String mail,
                                 String descripcion, Date fechaInicio, Date fechaFin, int puntaje,
                                 String categoria, String direccion, String imageUrl) {
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
        reporte.setDireccion(direccion);
        reporte.setImageUrl(imageUrl);
        return this.reportesRepository.save(reporte);
    }

    @Override
    public boolean isCritic(String barrio) {
        List<CantidadReportesPorCategoria> data = reportesRepository.getReportsByAllCategories(
                barrio, null, new Date());
        int cantidad = -1;
        for (CantidadReportesPorCategoria r :
                data) {
            if (DESAGUE_TAPADO.equalsIgnoreCase(r.getCategoria())) {
                cantidad = r.getCantidad();
            }
        }
        return cantidad > minimaCantidadReportes;
    }
}
