package com.panama.banc.services.reporte;

import com.panama.banc.entities.Reporte;

import java.time.LocalDate;
import java.util.List;

public interface IReporteService {
    List<Reporte> reporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente);
}
