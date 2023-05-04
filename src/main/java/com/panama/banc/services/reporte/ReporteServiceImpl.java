package com.panama.banc.services.reporte;

import com.panama.banc.entities.Reporte;
import com.panama.banc.repositories.reporte.ReporteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ReporteServiceImpl implements IReporteService{

    @Inject
    private ReporteRepository reporteRepository;
    @Override
    public List<Reporte> reporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente) {
        reporteRepository.generarReporte(fechaInicio, fechaFin, idCliente);
        return reporteRepository.generarReporte(fechaInicio, fechaFin, idCliente);
    }
}
