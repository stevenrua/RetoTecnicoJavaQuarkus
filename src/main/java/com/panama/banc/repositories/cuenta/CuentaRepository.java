package com.panama.banc.repositories.cuenta;

import com.panama.banc.entities.Cuenta;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface CuentaRepository extends PanacheRepositoryBase<Cuenta, Long> {
}
