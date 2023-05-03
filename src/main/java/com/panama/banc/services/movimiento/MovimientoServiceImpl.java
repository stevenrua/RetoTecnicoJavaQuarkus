package com.panama.banc.services.movimiento;

import com.panama.banc.entities.Cuenta;
import com.panama.banc.entities.Movimientos;
import com.panama.banc.entities.Reporte;
import com.panama.banc.repositories.movimiento.MovimientoRepository;
import com.panama.banc.services.cuenta.ICuentaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class MovimientoServiceImpl implements IMovimientoService {
    @Inject
    private MovimientoRepository movimientoRepository;
    @Inject
    private ICuentaService cuentaService;

    @Override
    public List<Movimientos> findAll() {
            return movimientoRepository.listAll();
    }

    @Override
    public Movimientos findById(Long id) {
        return movimientoRepository.findById(id);
    }

    @Override
    @Transactional
    public Movimientos save(Movimientos movimiento) {
        movimiento.setDate(LocalDate.now());
        realizarMovimiento(movimiento);
        movimientoRepository.persist(movimiento);
        return movimiento;
    }

    @Override
    public Movimientos update(Long id, Movimientos movimientos) {
        realizarMovimiento(movimientos);

        Movimientos movimientoById = movimientoRepository.findById(id);
        movimientoById.setDate(movimientos.getDate());
        movimientoById.setCuenta(movimientos.getCuenta());
        movimientoById.setValor(movimientos.getValor());
        movimientoById.setTipoMovimiento(movimientos.getTipoMovimiento());

        movimientoRepository.persist(movimientoById);
        return movimientoById;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public List<Reporte> reporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente) {
        movimientoRepository.generarReporte(fechaInicio, fechaFin, idCliente);
        return movimientoRepository.generarReporte(fechaInicio, fechaFin, idCliente);
    }

    private void realizarMovimiento(Movimientos movimiento){
        Long idCuenta = movimiento.getCuenta().getId();
        Cuenta cuentaById = cuentaService.findById(idCuenta);

        if(cuentaById != null){
            double saldoCuenta = cuentaById.getSaldoInicial();
            String tipoMovimiento = movimiento.getTipoMovimiento();
            movimiento.setSaldo(saldoCuenta);
            double saldoActual = (tipoMovimiento.equalsIgnoreCase("Retiro")) ?
                    comprobarMovimiento(movimiento.getValor(), saldoCuenta)
                    : (saldoCuenta + movimiento.getValor());
            if (saldoActual == 0.0) {
                throw new IllegalArgumentException(
                        "ERROR: Saldo Insificiente. Detail: Tiene un saldo de " + saldoCuenta
                                + ", insuficiente para realizar el retiro");
            } else {
                cuentaById.setSaldoInicial(saldoActual);
                cuentaService.update(idCuenta, cuentaById);
            }
        }
    }

    public double comprobarMovimiento(double valor, double saldoCuenta){
        boolean saldoSuficiente = (saldoCuenta == 0 || (saldoCuenta - valor) < 0) ? false : true;
        return (saldoSuficiente == true) ? (saldoCuenta - valor) : 0.0;
    }
}
