package com.panama.banc.services.cuenta;

import com.panama.banc.entities.Cliente;
import com.panama.banc.entities.Cuenta;

import java.util.List;

public interface ICuentaService {
    List<Cuenta> findAll();
    Cuenta findById(Long id);
    //boolean findByIdentificacion(String identificacion);
    Cuenta save(Cuenta cuenta);
    Cuenta update(Long id, Cuenta cuenta);
    void delete(Long id);
}
