package com.aquam.api.repositories;

import com.aquam.api.entities.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReportesRepository extends JpaRepository<Reporte, Long> {

    @Query(value = "SELECT * FROM reportes " +
            "WHERE :fechaActual BETWEEN fecha_inicio AND fecha_fin", nativeQuery = true)
    List<Reporte> getAllActiveReports(@Param("fechaActual") Date fechaActual);

    @Query(value = "SELECT * FROM reportes " +
            "WHERE :mail = mail AND :fechaActual BETWEEN fecha_inicio AND fecha_fin", nativeQuery = true)
    List<Reporte> getAllActiveReportsOfUser(@Param("mail") String mail,
                                       @Param("fechaActual") Date fechaActual);


}
