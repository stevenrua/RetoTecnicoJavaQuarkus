package com.panama.banc.repositories.movimiento;

import com.panama.banc.entities.Movimientos;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepository extends PanacheRepositoryBase<Movimientos, Long> {
    List<Object[]> generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente);
}
