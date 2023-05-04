package com.panama.banc.repositories.reporte;

import com.panama.banc.entities.Reporte;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReporteRepository extends PanacheRepository<Reporte> {
    List<Reporte> generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente);
}
