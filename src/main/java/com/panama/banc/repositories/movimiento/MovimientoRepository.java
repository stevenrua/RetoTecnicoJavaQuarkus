package com.panama.banc.repositories.movimiento;

import com.panama.banc.entities.Movimientos;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface MovimientoRepository extends PanacheRepositoryBase<Movimientos, Long> {
}
