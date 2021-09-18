package com.aquam.api.repositories;

import com.aquam.api.dtos.CantidadReportesPorCategoria;
import com.aquam.api.entities.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReportesRepository extends JpaRepository<Reporte, Long> {

    @Query(value = "SELECT * FROM reportes " +
            "WHERE :fechaActual BETWEEN fecha_inicio AND fecha_fin " +
            "AND (:mail IS NULL OR upper(cast(:mail as varchar)) = upper(mail)) " +
            "AND (:barrio IS NULL OR upper(cast(:barrio as varchar)) = upper(barrio))", nativeQuery = true)
    List<Reporte> getAllActiveReports(@Param("barrio") String barrio,
                                      @Param("mail") String mail,
                                      @Param("fechaActual") Date fechaActual);

    @Query(value = "UPDATE reportes " +
            "SET activado = false WHERE :id = id", nativeQuery = true)
    Reporte deleteReporte(@Param("id") long id);

    @Query(value = "SELECT COUNT(*) AS cantidad, categoria FROM reportes " +
            "WHERE :fechaActual BETWEEN fecha_inicio AND fecha_fin " +
            "AND (:mail IS NULL OR upper(cast(:mail as varchar)) = upper(mail)) " +
            "AND (:barrio IS NULL OR upper(cast(:barrio as varchar)) = upper(barrio)) " +
            "GROUP BY categoria", nativeQuery = true)
    List<CantidadReportesPorCategoria> getReportsByAllCategories(@Param("barrio") String barrio,
                                                                 @Param("mail") String mail,
                                                                 @Param("fechaActual") Date fechaActual);



}
