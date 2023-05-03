package com.panama.banc.services.movimiento;

import com.panama.banc.entities.Movimientos;
import com.panama.banc.entities.Reporte;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IMovimientoService {
    List<Movimientos> findAll();
    Movimientos findById(Long id);
    //boolean findByIdentificacion(String identificacion);
    Movimientos save(Movimientos movimientos);
    Movimientos update(Long id, Movimientos movimientos);
    void delete(Long id);
    List<Reporte> reporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente);
}
