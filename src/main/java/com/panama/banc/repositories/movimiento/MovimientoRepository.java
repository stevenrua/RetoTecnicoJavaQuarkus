package com.panama.banc.repositories.movimiento;

import com.panama.banc.entities.Movimientos;
import com.panama.banc.entities.Reporte;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepository extends PanacheRepositoryBase<Movimientos, Long> {
    List<Reporte> generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente);
}
