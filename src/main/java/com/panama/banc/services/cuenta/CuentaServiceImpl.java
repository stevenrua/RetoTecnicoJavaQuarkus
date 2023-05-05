package com.panama.banc.services.cuenta;

import com.panama.banc.entities.Cuenta;
import com.panama.banc.repositories.cuenta.CuentaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CuentaServiceImpl implements ICuentaService {

    @Inject
    private CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public List<Cuenta> findAll() {
        return cuentaRepository.listAll();
    }

    @Override
    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    @Transactional
    public Cuenta save(Cuenta cuenta) {
        cuentaRepository.persist(cuenta);
        return cuenta;
    }

    @Override
    @Transactional
    public Cuenta update(Long id, Cuenta cuenta) {
        Cuenta cuentaById = cuentaRepository.findById(id);
        cuentaById.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaById.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaById.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaById.setEstado(cuenta.isEstado());
        cuentaById.setCliente(cuenta.getCliente());
        cuentaRepository.persist(cuentaById);
        return cuentaById;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cuentaRepository.deleteById(id);
    }
}
